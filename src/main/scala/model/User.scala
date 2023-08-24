package model

import scala.util.Random

case class User (id: Long, firstName: String, lastName: String, age: Int)

object User {
  def from(firstName: String, lastName: String, age: Int): User = {
    User(randomId, firstName, lastName, age)
  }

  private def randomId: Int = {
    val rd = new Random()
    rd.nextInt(5000)
  }
}
