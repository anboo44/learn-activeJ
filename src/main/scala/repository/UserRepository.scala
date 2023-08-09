package repository

import io.activej.inject.annotation.Inject
import model.User

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

@Inject
class UserRepository {
  private val storage = ArrayBuffer[User]()

  def store(user: User): Try[Unit] = Try {
    val isExisted = storage.exists(_.id == user.id)

    if (isExisted) throw new Exception("User is existed")
    else storage += user
  }

  def update(user: User): Try[Unit] = Try {
    val userOpt = storage.find(_.id == user.id)

    if (userOpt.isDefined) {
      storage -= userOpt.get
      storage += user
    } else throw new Exception("User is not existed")
  }

  def getById(id: Long): Option[User] = storage.find(_.id == id)

  def getAll: Seq[User] = storage.toSeq

  def deleteById(id: Long): Try[Unit] = Try {
    storage.find(_.id == id)
           .map(user => storage -= user)
           .getOrElse(throw new Exception("User is not existed"))
  }
}
