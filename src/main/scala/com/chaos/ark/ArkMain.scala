package com.chaos.ark

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.chaos.ark.actors.{DispatchActor, FileActor, KafkaActor}
import com.chaos.ark.models.Creature
import com.chaos.ark.modules.{ConfigSupport, JsonSupport}

import scala.io.StdIn


/**
  * Created by zcfrank1st on 06/02/2017.
  */
object ArkMain extends App with JsonSupport with ConfigSupport {
  val port = config.getInt("ark.api.port")
  val host = config.getString("ark.api.host")

  implicit val system = ActorSystem("ark")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val dispatchActor = system.actorOf(Props[DispatchActor])

  val route =
    post {
      path("ark") {
        entity(as[Creature]) { creature =>
          dispatchActor ! creature
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
