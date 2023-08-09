import controller.UserController
import io.activej.http.{ AsyncServlet, HttpRequest, RoutingServlet }
import io.activej.http.HttpMethod.{ DELETE, GET, POST }
import io.activej.inject.annotation.Provides
import io.activej.launchers.http.HttpServerLauncher

object Main extends HttpServerLauncher {

  @Provides
  def servlet(userController: UserController): AsyncServlet = {
    RoutingServlet.create
      .map(POST, "/users", (req: HttpRequest) => userController.save(req))
      .map(GET, "/users", (_: HttpRequest) => userController.getAll)
      .map(GET, "/users/:id", (req: HttpRequest) => userController.getById(req))
      .map(DELETE, "/users/:id", (req: HttpRequest) => userController.deleteById(req))
  }

  def main(args: Array[String]): Unit = {
    Main.launch(args)
  }
}