package com.chaos.ark

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.chaos.ark.actors.DispatchActor
import com.chaos.ark.models.Creature
import com.chaos.ark.modules.{ConfigSupport, JsonSupport}

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._

import scala.io.StdIn


/**
  * Created by zcfrank1st on 06/02/2017.
  */
object ArkMain extends App with ConfigSupport with JsonSupport {

  implicit val system = ActorSystem("ark")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val dispatchActor = system.actorOf(Props[DispatchActor])

  val route =
    path("ark") {
      post {
        entity(as[Creature]) { creature =>
          dispatchActor ! creature
          complete("ok")
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, apiHost, apiPort)

  println(s"Server online at http://$apiHost:$apiPort/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
