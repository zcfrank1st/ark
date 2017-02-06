package com.chaos.ark.modules

import com.chaos.ark.models.Creature
import spray.json.DefaultJsonProtocol.jsonFormat1

/**
  * Created by zcfrank1st on 06/02/2017.
  */
// TODO change json format
trait JsonSupport {
  implicit val creatureFormat = jsonFormat1(Creature)
}
