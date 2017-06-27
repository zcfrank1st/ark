package com.chaos.ark.actors

import akka.actor.Actor
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import com.chaos.ark.models.KafkaMessage
import com.chaos.ark.modules.{ConfigSupport, JsonSupport}
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.{ByteArraySerializer, StringSerializer}

/**
  * Created by zcfrank1st on 06/02/2017.
  */
class KafkaActor extends Actor with ConfigSupport with JsonSupport {
  // TODO test if ok
  private val producerSettings: ProducerSettings[Array[Byte], String] =
    ProducerSettings(context.system, new ByteArraySerializer, new StringSerializer).withBootstrapServers(kafkaServers)

  private implicit val materializer = ActorMaterializer()

  override def receive: Receive = {
    case c @ KafkaMessage(_, _) =>
      Source
        .single(c)
        .map(ele =>
          new ProducerRecord[Array[Byte], String](ele.channel, gson.toJson(ele.content)))
        .runWith(Producer.plainSink(producerSettings))
    case _ => // nothing to do
  }
}
