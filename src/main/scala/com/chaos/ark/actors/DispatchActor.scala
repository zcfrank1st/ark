package com.chaos.ark.actors

import akka.actor.{Actor, Props}
import com.chaos.ark.models.{Creature, KafkaMessage}

/**
  * Created by zcfrank1st on 06/02/2017.
  */
class DispatchActor extends Actor {

  override def receive: Receive = {
    case Creature(typ, ct, cl) =>
      typ match {
        case 0 =>
          val fileTask = context.actorOf(Props[FileActor])
          fileTask ! Creature(typ, ct, cl)
        case 1 =>
          val kafkaTask = context.actorOf(Props[KafkaActor])
          val channel = cl.getOrElse("")

          if ("" != channel)
            kafkaTask ! KafkaMessage(ct, channel)
      }
    case _ =>
    // do nothing
  }
}
