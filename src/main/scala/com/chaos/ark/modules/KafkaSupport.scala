package com.chaos.ark.modules

import akka.Done
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import org.apache.kafka.clients.producer.ProducerRecord

import scala.concurrent.Future

/**
  * Created by zcfrank1st on 06/02/2017.
  */
trait KafkaSupport {
  def sendToKafka(content: String, channel: String)(implicit producerSettings: ProducerSettings[Array[Byte], String]): Future[Done] = {
    Source
      .single()
      .map(_ =>
        new ProducerRecord[Array[Byte], String](channel, content))
      .runWith(Producer.plainSink(producerSettings))
  }
}
