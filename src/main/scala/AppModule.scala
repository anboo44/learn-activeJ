import controller.UserController
import io.activej.http.{ AsyncServlet, HttpRequest, RoutingServlet }
import io.activej.http.HttpMethod.{ DELETE, GET, POST }
import io.activej.inject.annotation.Provides
import io.activej.inject.module.AbstractModule
import repository.{ DBMigration, UserRepository }
import service.UserService

class AppModule extends AbstractModule {

  @Provides
  def userRepository(): UserRepository = {
    DBMigration.migrate()
    new UserRepository()
  }

  @Provides
  def userService(userRepository: UserRepository): UserService = new UserService(userRepository)

  @Provides
  def userController(userService: UserService): UserController = new UserController(userService)

  @Provides
  def servlet(userController: UserController): AsyncServlet = {
    RoutingServlet.create
                  .map(POST, "/users", (req: HttpRequest) => userController.save(req))
                  .map(GET, "/users", (_: HttpRequest) => userController.getAll)
                  .map(GET, "/users/:id", (req: HttpRequest) => userController.getById(req))
                  .map(DELETE, "/users/:id", (req: HttpRequest) => userController.deleteById(req))
  }
}
