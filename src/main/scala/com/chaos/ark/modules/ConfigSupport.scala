package com.chaos.ark.modules

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by zcfrank1st on 06/02/2017.
  */
trait ConfigSupport {
  private val config: Config = ConfigFactory.load("ark")

  val apiPort: Int = config.getInt("ark.api.port")
  val apiHost: String = config.getString("ark.api.host")
  val kafkaServers: String = config.getString("ark.kafka.servers")
}
