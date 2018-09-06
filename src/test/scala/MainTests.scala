import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

import scala.collection.mutable
import scala.util.Random

class MainTests extends FeatureSpec with GivenWhenThen with Matchers {
  info("Write a program that given standard input with one email address per line")
  info("outputs the 10 domains (or less if there aren't that many) that appear the most often")
  info("with a count of the number of times it appears after each domain.")

  feature("Outputs the 10 domains or less domains that appear the most often") {
    scenario("No domain present") {

      Given("An empty list of emails")
      val input = Array[String]()

      When("executing the program")
      val output = Main.outputFirstTenDomains(input)

      Then("no domain should be return as output")
      output shouldBe empty
    }
    scenario("Collect input domains") {

      val expected: Array[String] = "yahoo gmail live".split(" ")

      Given("A list of emails with less than 10 domains")

      val input = expected.flatMap { domain =>
        (1 to 5) map(_ => s"${(Random.alphanumeric take 10 mkString).toLowerCase}@$domain.com")
      }

      When("executing the program")
      input.foreach(println)
      val output = Main.outputFirstTenDomains(input)

      Then("all the input domains should be retrieved")
      output should contain theSameElementsAs expected
    }
  }
}
