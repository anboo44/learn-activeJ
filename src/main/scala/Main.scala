import io.activej.http.{ AsyncServlet, HttpResponse }
import io.activej.inject.annotation.Provides
import io.activej.launchers.http.HttpServerLauncher

object Main extends HttpServerLauncher {

  @Provides
  def servlet: AsyncServlet = _ => HttpResponse.ok200.withPlainText("Hello World")

  def main(args: Array[String]): Unit = {
    Main.launch(args)
  }
}