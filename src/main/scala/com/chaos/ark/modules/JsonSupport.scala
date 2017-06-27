package com.chaos.ark.modules

import com.chaos.ark.models.Creature
import com.google.gson.Gson
import spray.json.DefaultJsonProtocol._
import spray.json.RootJsonFormat

/**
  * Created by zcfrank1st on 06/02/2017.
  */
trait JsonSupport {
  implicit val creatureFormat: RootJsonFormat[Creature] = jsonFormat3(Creature)

  val gson = new Gson()
}
