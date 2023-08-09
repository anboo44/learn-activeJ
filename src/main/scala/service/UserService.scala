package service

import controller.UserDTO
import io.activej.inject.annotation.Inject
import model.User
import repository.UserRepository

import scala.util.Try

@Inject
class UserService {

  @Inject var userRepository: UserRepository = _

  def store(firstName: String, lastName: String, age: Int): Try[User] = {
    val user = User(firstName, lastName, age)
    userRepository.store(user).map(_ => user)
  }

  def getAll: Seq[UserDTO] = userRepository.getAll.map(UserDTO(_))

  def getById(id: Long): Option[UserDTO] = userRepository.getById(id).map(UserDTO(_))

  def deleteById(id: Long): Try[Unit] = userRepository.deleteById(id)
}
