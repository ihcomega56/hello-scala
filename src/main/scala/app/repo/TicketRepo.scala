package app.repo

import app.models.{Bug, Issue, Ticket, TicketStatus}

object TicketRepo {

  // このクラスでしか見えない
//  type TicketId = Long

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

  // TODO 戻り値をオプションにする
  def findIssuesByStatus(status: String): Seq[Issue] = {
    tickets.collect {
      case (id, ticket: Issue) if TicketStatus.isMatchedStatus(status, ticket) => ticket
    }.toSeq
  }

  // TODO 戻り値をオプションにする
  def findBugByStatus(status: String): Seq[Bug] = {
    tickets.collect {
      case (id, ticket: Bug) if TicketStatus.isMatchedStatus(status, ticket) => ticket
    }.toSeq
  }

  // TODO 実装する
  def fix(id: TicketId): Boolean = { false }

}
