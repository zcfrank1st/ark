package com.chaos.ark.modules

import com.chaos.ark.models.{Creature, Log}
import com.google.gson.Gson
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

/**
  * Created by zcfrank1st on 06/02/2017.
  */
trait JsonSupport {
  implicit val logFormat: RootJsonFormat[Log] = jsonFormat9(Log)
  implicit val creatureFormat: RootJsonFormat[Creature] = jsonFormat3(Creature)

  val gson = new Gson()
}
