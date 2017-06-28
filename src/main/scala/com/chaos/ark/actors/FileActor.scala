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
      log.info(s"${ct.actionId}\t${ct.pageId}\t${ct.moduleId}\t${ct.elementId}\t${ct.referActionId}\t${ct.path}\t${ct.param}\t${ct.mid}\t${ct.timestamp}\t${ct.extra}")

    case _ => // nothing to do
  }
}
