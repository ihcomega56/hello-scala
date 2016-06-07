package app.repo

import app.models.{Bug, Issue, TicketStatus}
import org.scalatest.{FlatSpec, Matchers}

class TicketRepoSpec extends FlatSpec with Matchers {

  val issue1 = new Issue(1, "タイトル1")

  val bug1 = new Bug(2, "タイトル2", "ディスクリプション2")

  val issue2 = new Issue(3, "タイトル3", TicketStatus.Fixed)

  val bug2 = new Bug(4, "タイトル4", "ディスクリプション4", TicketStatus.Fixed)

  "createIssue" should "returns all tickets" in {
    TicketRepo.createIssue("タイトル1") should be === issue1

  }

  "createBug" should "returns all tickets" in {
    TicketRepo.createBug("タイトル2", "ディスクリプション2") should be === bug1
  }

  "findAll" should "returns all tickets" in {
    TicketRepo.findAll should be === Seq(issue1, bug1)

  }

  "findById" should "finds a ticket by ticket id" in {
    TicketRepo.findById(1) should be === Some(issue1)
    TicketRepo.findById(2) should be === Some(bug1)
    TicketRepo.findById(3) should be === None
  }

  "findIssuesByStatus" should "finds issues by ticket status" in {
    TicketRepo.findIssuesByStatus("open") should be === Some(Seq(issue1))
    TicketRepo.findIssuesByStatus("fixed") should be === None
    TicketRepo.createIssue("タイトル3")
    TicketRepo.fix(3)
    TicketRepo.findIssuesByStatus("fixed") should be === Some(Seq(issue2))
  }

  "findBugsByStatus" should "finds bugs by ticket status" in {
    TicketRepo.findBugsByStatus("open") should be === Some(Seq(bug1))
    TicketRepo.findBugsByStatus("fixed") should be === None
    TicketRepo.createBug("タイトル4", "ディスクリプション4")
    TicketRepo.fix(4)
    TicketRepo.findBugsByStatus("fixed") should be === Some(Seq(bug2))
  }

  "fix" should "changes the status from open to fixed" in {
    TicketRepo.fix(1) should be === true
    TicketRepo.fix(2) should be === true
    TicketRepo.findById(1).get.status should be === TicketStatus.Fixed
    TicketRepo.findById(2).get.status should be === TicketStatus.Fixed
    TicketRepo.fix(3) should be === false
    TicketRepo.fix(4) should be === false
  }
}
