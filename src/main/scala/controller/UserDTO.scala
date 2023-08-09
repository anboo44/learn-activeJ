package controller

import play.api.libs.json.{ Json, OWrites }

case class UserDTO (id: Long, name: String, age: Int)

object UserDTO {

  implicit val writer: OWrites[UserDTO] = Json.writes[UserDTO]
}
