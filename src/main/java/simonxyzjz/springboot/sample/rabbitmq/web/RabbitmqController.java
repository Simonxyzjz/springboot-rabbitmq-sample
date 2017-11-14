package simonxyzjz.springboot.sample.rabbitmq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import simonxyzjz.springboot.sample.rabbitmq.message.InboundMessage;
import simonxyzjz.springboot.sample.rabbitmq.producer.InboundMessageProducer;

@RestController
@RequestMapping("/rmq")
public class RabbitmqController {
	
	@Autowired
	private InboundMessageProducer inProducer;
	
	
	@ResponseBody
	@RequestMapping("/produce")
	public ResponseEntity<?> produceInMessage(String content) {
		InboundMessage message = new InboundMessage();
		message.setPayload(content);
		inProducer.convertAndSend(message);
		return ResponseEntity.ok("ok");
	}

}
