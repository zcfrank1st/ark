package com.chaos.ark.models

/**
  * Created by zcfrank1st on 06/02/2017.
  */
final case class Log(
  actionId: String,
  pageId: String,
  moduleId: String,
  elementId: String,
  referActionId: String,

  path: String,
  param: String,

  mid: String,
  timestamp: String ,
  extra: String
                    )

// json format
final case class Creature(typ: Int, content: Log, channel: Option[String] = None)

// simple case
final case class KafkaMessage(content: Log, channel: String)
