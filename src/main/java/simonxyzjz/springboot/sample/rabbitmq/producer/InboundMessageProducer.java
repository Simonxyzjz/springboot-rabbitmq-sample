package simonxyzjz.springboot.sample.rabbitmq.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import simonxyzjz.springboot.sample.rabbitmq.config.RabbitmqProperties;
import simonxyzjz.springboot.sample.rabbitmq.message.InboundMessage;

@Slf4j
@Service
public class InboundMessageProducer implements ConfirmCallback {

	@Autowired
	private RabbitmqProperties rabbitmqProperties;
	
	private RabbitTemplate rabbitTemplate;
	
	public InboundMessageProducer(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
		this.rabbitTemplate.setConfirmCallback(this);
	}
	
	/** 
	 * CallbackConfirm only means that the message was accepted/rejected by rabbitmq server
	 */
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		log.info("ConfirmCallback: message[{}], ack[{}], cause[{}]", correlationData.getId(), ack, cause);
	}

	public void convertAndSend(InboundMessage message) {
		log.info("produce message...start");
		String uuid = UUID.randomUUID().toString();
		CorrelationData correlationId = new CorrelationData(uuid);
		rabbitTemplate.convertAndSend(rabbitmqProperties.getDirectExchangeName(), rabbitmqProperties.getRoutingKey(),
				message, correlationId);
		log.info("produce message...end");
	}

}
