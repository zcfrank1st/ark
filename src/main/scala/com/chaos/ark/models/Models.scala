package com.chaos.ark.models

/**
  * Created by zcfrank1st on 06/02/2017.
  */
// json format
final case class Creature(typ: Int, content: String, channel: Option[String] = None)

// simple case
final case class KafkaMessage(content: String, channel: String)
