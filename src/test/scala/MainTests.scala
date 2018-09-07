import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

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

      val expected = Map(
        "yahoo" -> 123,
        "gmail" -> 1234,
        "live" -> 54321,
      )

      Given("A list of emails with less than 10 domains")

      val input = expected.flatMap {
        case (domain, frequency) =>
          (1 to frequency) map (_ => s"${Random.alphanumeric take 10 mkString}@$domain.com")
      }.toArray

      When("executing the program")
      val output = Main.outputFirstTenDomains(input)

      Then("all the input domains should be retrieved")
      output should contain theSameElementsAs expected
    }
    scenario("Collect input domains when more than 10") {

      val expected = Map(
        "yahoo" -> 1234,
        "gmail" -> 4321,
        "live" -> 323,
        "yandex" -> 4000,
        "tin" -> 13,
        "outlook" -> 32,
        "credem" -> 53,
        "unicredit" -> 111,
        "kahoot" -> 90,
        "messenger" -> 100,
      )

      val inputDomainsWithFrequency = Map(
        ("msn", 11),
        ("fake", 12)
      ) ++ expected

      Given("A list of emails with 12 different domains")

      val input = inputDomainsWithFrequency.flatMap {
        case (domain, frequency) =>
          (1 to frequency) map (_ => s"${Random.alphanumeric take 10 mkString}@$domain.com")
      }.toArray

      When("executing the program")
      val output = Main.outputFirstTenDomains(input)

      Then("Just the 10 most frequent domains should be returned with their frequency")
      output should contain theSameElementsAs expected
    }
  }
}