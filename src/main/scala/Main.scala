import scala.collection.immutable.ListMap
import scala.util.matching.Regex

object Main {
  val EmailPattern: Regex =
    """[A-z0-9]+@([A-z0-9]+)\.[A-z0-9]+""".r

  def main(args: Array[String]): Unit = {
    outputFirstTenDomains(args).foreach {
      case (element, frequency) =>
        println(s"$element $frequency")
    }
  }

  def outputFirstTenDomains(emails: Array[String]): Map[String, Int] = {
    val domainAndFrequencies = emails.flatMap {
      case EmailPattern(domain) =>
        Some(domain)
      case _ =>
        None
    }.groupBy(identity).collect {
      case (element, occurrences) =>
        (element, occurrences.length)
    }
    ListMap(domainAndFrequencies.toSeq.sortWith(_._2 > _._2).take(10):_*)
  }
}