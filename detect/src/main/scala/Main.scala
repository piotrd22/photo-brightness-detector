import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import ToFiles._
import ToFutures._

object Main {

  @main def mainFunc() = {
    println("Click enter to continue. If you want to override the default cutOff value (80) enter an integer (80 - 85 recommended)")

    val aFuture = ToFutures.aFutures()

    val futureSeq = Future.sequence(aFuture)
    Await.ready(futureSeq, Duration.Inf)

  }
}