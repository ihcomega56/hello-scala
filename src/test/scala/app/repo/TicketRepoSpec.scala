package app.repo

import app.models.{Bug, Issue}
import org.scalatest.{FlatSpec, Matchers}

class TicketRepoSpec extends FlatSpec with Matchers {

  val issue1 = new Issue(1, "タイトル1")

  val bug1 = new Bug(2, "タイトル2", "ディスクリプション2")

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
  }

  "findBugsByStatus" should "finds bugs by ticket status" in {
  }

  "fix" should "finds bugs by ticket status" in {
  }
}
