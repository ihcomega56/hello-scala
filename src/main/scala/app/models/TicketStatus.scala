package app.models

sealed trait TicketStatus {

}

object TicketStatus {

  case object Open extends TicketStatus

  case object Fixed extends TicketStatus

  def isMatchedStatus(status: String, ticket: Ticket) = {
    status match {
      case "open" => ticket.status == TicketStatus.Open
      case "fixed" => ticket.status == TicketStatus.Fixed
      case _ => false
    }
  }

}
