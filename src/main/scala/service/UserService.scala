package service

import controller.UserDTO
import model.User
import repository.UserRepository

import scala.util.Try

class UserService(userRepository: UserRepository) {

  def store(firstName: String, lastName: String, age: Int): Try[User] = {
    val user = User.from(firstName, lastName, age)
    userRepository.store(user).map(_ => user)
  }

  def getAll: Seq[UserDTO] = userRepository.getAll.map(user => {
    UserDTO(
      user.id,
      mergeName(user.firstName, user.lastName),
      user.age
    )
  })

  def getById(id: Long): Option[UserDTO] = userRepository.getById(id).map(user => {
    UserDTO(
      user.id,
      mergeName(user.firstName, user.lastName),
      user.age
    )
  })

  def deleteById(id: Long): Try[Unit] = userRepository.deleteById(id)
  def update(user: User): Try[Unit] = userRepository.update(user)

  private def mergeName(firstName: String, lastName: String): String = s"$firstName $lastName"
}
