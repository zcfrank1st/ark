package com.chaos.ark.modules

import com.typesafe.config.ConfigFactory

/**
  * Created by zcfrank1st on 06/02/2017.
  */
trait ConfigSupport {
  val config = ConfigFactory.load("ark")
}
