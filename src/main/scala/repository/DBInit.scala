package repository

import slick.jdbc.JdbcBackend.Database

import scala.concurrent.{ Await, Future }
import scala.concurrent.duration.Duration
import scala.language.implicitConversions

private[repository] object DBInit {
  val username: String = "activej"
  val password: String = "123456"
  val url: String = s"jdbc:mysql://localhost:3306/scala-activej"

  val db = Database.forURL(url, username, password, driver = "com.mysql.cj.jdbc.Driver")
  def awaitResult[T](result: Future[T]): T = Await.result(result, Duration.Inf)
}
