package repository

import model.User
import repository.DBInit._
import slick.jdbc.MySQLProfile.api._
import slick.lifted.TableQuery

import scala.util.Try

class UserRepository {
  private val dao =  TableQuery[UserDAO]

  def store(user: User): Try[Unit] = Try {
    getById(user.id) match {
      case Some(_) => throw new Exception("User existed")
      case _ => awaitResult(db.run { dao += user })
    }
  }

  def update(user: User): Try[Unit] = Try {
    getById(user.id) match {
      case None => throw new Exception("User doesn't exist")
      case _ => awaitResult(db.run { dao.insertOrUpdate(user) })
    }
  }

  def getById(id: Long): Option[User] = {
    awaitResult(db.run { dao.filter(_.id === id).result }).headOption
  }

  def getAll: Seq[User] = awaitResult(db.run { dao.result })

  def deleteById(id: Long): Try[Unit] = Try {
    getById(id) match {
      case None => throw new Exception("User doesn't exist")
      case _ => awaitResult(db.run { dao.filter(_.id === id).delete })
    }
  }
}
