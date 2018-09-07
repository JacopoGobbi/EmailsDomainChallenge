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

  def outputFirstTenDomains(emails: Array[String]): Array[(String, Int)] =
    emails.flatMap {
      case EmailPattern(domain) =>
        Some(domain)
      case _ =>
        None
    }.groupBy(identity).collect {
      case (element, occurrences) =>
        (element, occurrences.length)
    }.toArray
}