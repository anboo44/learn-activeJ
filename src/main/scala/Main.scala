import io.activej.inject.module.ModuleBuilder
import io.activej.launchers.http.HttpServerLauncher

object Main extends HttpServerLauncher {

  override def getBusinessLogicModule = {
    ModuleBuilder.create
      .install(new AppModule)
      .build
  }

  def main(args: Array[String]): Unit = {
    Main.launch(args)
  }
}