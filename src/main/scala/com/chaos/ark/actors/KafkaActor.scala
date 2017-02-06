package com.chaos.ark.actors

import akka.actor.Actor
import akka.kafka.ProducerSettings
import com.chaos.ark.models.KafkaMessage
import com.chaos.ark.modules.{ConfigSupport, KafkaSupport}
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

/**
  * Created by zcfrank1st on 06/02/2017.
  */
class KafkaActor extends Actor with KafkaSupport with ConfigSupport {
  implicit val producerSettings: ProducerSettings[Array[Byte], String] =
    ProducerSettings(context.system, new ByteArraySerializer, new StringSerializer).withBootstrapServers(kafkaServers)

  override def receive: Receive = {
    case c @ KafkaMessage(_, _) =>
      sendToKafka(c)
    case _ => // nothing to do
  }
}
