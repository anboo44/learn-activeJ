package controller

import model.User
import play.api.libs.json.{ Json, OWrites }

case class UserDTO (id: Long, name: String, age: Int)

object UserDTO {

  implicit val writer: OWrites[UserDTO] = Json.writes[UserDTO]

  def apply(user: User): UserDTO = {
    UserDTO(user.id, s"${user.firstName} ${user.lastName}", user.age)
  }
}
