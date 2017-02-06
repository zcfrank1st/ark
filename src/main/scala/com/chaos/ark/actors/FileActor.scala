package com.chaos.ark.actors

import akka.actor.Actor
import akka.event.Logging
import com.chaos.ark.models.Creature

/**
  * Created by zcfrank1st on 06/02/2017.
  */
class FileActor extends Actor {
  val log = Logging(context.system, this)

  override def receive: Receive = {
    case Creature(_, ct, _) =>
      log.info(ct)

    case _ => // nothing to do
  }
}
