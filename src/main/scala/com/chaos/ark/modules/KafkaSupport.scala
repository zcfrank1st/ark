package com.chaos.ark.modules

import akka.Done
import akka.kafka.ProducerSettings
import akka.kafka.scaladsl.Producer
import akka.stream.scaladsl.Source
import com.chaos.ark.models.KafkaMessage
import org.apache.kafka.clients.producer.ProducerRecord

import scala.concurrent.Future

/**
  * Created by zcfrank1st on 06/02/2017.
  */
trait KafkaSupport {
  def sendToKafka(kafkaMessage: KafkaMessage)(implicit producerSettings: ProducerSettings[Array[Byte], String]): Future[Done] = {
    Source
      .single(kafkaMessage)
      .map(ele =>
        new ProducerRecord[Array[Byte], String](ele.channel, ele.content))
      .runWith(Producer.plainSink(producerSettings))
  }
}
