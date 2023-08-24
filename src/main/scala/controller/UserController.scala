package controller

import io.activej.http.{ HttpRequest, HttpResponse }
import io.activej.promise.Promise
import play.api.libs.json.Json
import service.UserService

import scala.util.{ Failure, Success }

class UserController(userService: UserService) {

  def save(request: HttpRequest): Promise[HttpResponse] = {
    request
      .loadBody()
      .map(_ => new String(request.getBody.getArray))
      .map(bodyStr => Json.parse(bodyStr))
      .map { json =>
        val firstName = (json \ "firstName").as[String]
        val lastName = (json \ "lastName").as[String]
        val age = (json \ "age").as[Int]

        val result = userService.store(firstName, lastName, age)
        result match {
          case Success(user) => HttpResponse.ok200().withPlainText(s"User is stored with id: ${ user.id}")
          case Failure(ex)   => HttpResponse.ofCode(400).withPlainText(ex.getMessage)
        }
      }
  }

  // Impl same as fn save
  def update(request: HttpRequest): Promise[HttpResponse] = ???

  def getAll: HttpResponse = {
    val json = Json.toJson(userService.getAll)
    HttpResponse.ok200().withJson(json.toString())
  }

  def getById(request: HttpRequest): HttpResponse = {
    val id = request.getPathParameter("id").toInt

    userService.getById(id) match {
      case Some(user) => HttpResponse.ok200().withJson(Json.toJson(user).toString())
      case None => HttpResponse.ofCode(400).withPlainText("User is not existed")
    }
  }

  def deleteById(request: HttpRequest): HttpResponse = {
    val id = request.getPathParameter("id").toInt

    userService.deleteById(id) match {
      case Success(_) => HttpResponse.ok200().withPlainText("User is deleted")
      case Failure(ex) => HttpResponse.ofCode(400).withPlainText(ex.getMessage)
    }
  }
}
