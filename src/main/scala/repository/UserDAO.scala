package repository

import model.User
import slick.jdbc.MySQLProfile.api._
import slick.lifted.ProvenShape

private[repository] class UserDAO(tag: Tag) extends Table[User](tag, None, "users"){

  val id: Rep[Long] = column[Long]("id", O.Unique, O.PrimaryKey)
  val firstName: Rep[String] = column[String]("first_name")
  val lastName: Rep[String] = column[String]("last_name")
  val age: Rep[Int] = column[Int]("age")

  override def * : ProvenShape[User] = (id, firstName, lastName, age) <> ((User.apply _).tupled, User.unapply)
}
