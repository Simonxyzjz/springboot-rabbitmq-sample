package simonxyzjz.springboot.sample.rabbitmq.consumer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;
import simonxyzjz.springboot.sample.rabbitmq.message.InboundMessage;

@Slf4j
@Component
public class InboundMessageConsumer {
	private final CountDownLatch latch = new CountDownLatch(1);

	@RabbitListener(queues = "${rabbitmq-sample.inbound-queue}")
	public void receive(InboundMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
			throws IOException, InterruptedException {
		log.info("Consuming message[{}], deliveryTag[{}]", message.getPayload(), tag);
		channel.basicAck(tag, false);
		log.info("Consumed!");
		latch.countDown();
	}
}
