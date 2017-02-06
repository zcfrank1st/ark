package com.chaos.ark

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.chaos.ark.models.Creature
import com.chaos.ark.modules.{ConfigSupport, JsonSupport}

import scala.io.StdIn


/**
  * Created by zcfrank1st on 06/02/2017.
  */
object ArkMain extends App with JsonSupport with ConfigSupport {
  val port = config.getInt("ark.port")
  val host = config.getString("ark.host")

  implicit val system = ActorSystem("ark")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route =
    post {
      path("ark") {
        entity(as[Creature]) { creature =>
          // TODO send message to actor

          complete("ok")
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, host, port)

  println(s"Server online at http://$host:$port/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
