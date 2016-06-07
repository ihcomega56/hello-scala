package app.repo

import app.models.{Bug, Issue, Ticket, TicketStatus}

object TicketRepo {

  private var tickets: Map[TicketId, Ticket] = Map()

  private var currentId: TicketId = 1

  def findAll: Seq[Ticket] = {
    tickets.values.toSeq
  }

  def findById(id: TicketId): Option[Ticket] = {
    tickets.get(id)
  }

  def createIssue(title: String): Issue = {
    val newIssue = new Issue(currentId, title)
    tickets = tickets + (currentId -> newIssue)
    currentId += 1
    newIssue
  }

  def createBug(title: String, description: String): Bug = {
    val newBug = new Bug(currentId, title, description)
    tickets = tickets + (currentId -> newBug)
    currentId += 1
    newBug
  }

  def findIssuesByStatus(status: String): Option[Seq[Issue]] = {
    val issues = tickets.collect {
      case (id, ticket: Issue) if TicketStatus.isMatchedStatus(status, ticket) => ticket
    }.toSeq
    if (issues.nonEmpty) Some(issues) else None
  }

  def findBugsByStatus(status: String): Option[Seq[Bug]] = {
    val bugs = tickets.collect {
      case (id, ticket: Bug) if TicketStatus.isMatchedStatus(status, ticket) => ticket
    }.toSeq
    if (bugs.nonEmpty) Some(bugs) else None
  }

  def fix(id: TicketId): Boolean = {
    findById(id) match {
      case Some(t: Issue) =>
        if (t.status == TicketStatus.Open)
          tickets = tickets.updated(id, new Issue(t.id, t.title, TicketStatus.Fixed))
        else return false
      case Some(t: Bug) =>
        if (t.status == TicketStatus.Open)
          tickets = tickets.updated(id, new Bug(t.id, t.title, t.description, TicketStatus.Fixed))
        else return false
      case _ => return false
    }
    true
  }

}
