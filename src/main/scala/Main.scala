import scala.util.matching.Regex

object Main {
  val EmailPattern: Regex =
    """[A-z0-9]+@([A-z0-9]+)\.[A-z0-9]+""".r

  def main(args: Array[String]): Unit = {
    outputFirstTenDomains(args).foreach(println)
  }

  def outputFirstTenDomains(emails: Array[String]): Array[String] =
    emails.flatMap {
      case EmailPattern(domain) =>
        Some(domain)
      case _ =>
        None
    }.groupBy(identity).collect {
      case (x, _) =>
        x
    }.toArray
}