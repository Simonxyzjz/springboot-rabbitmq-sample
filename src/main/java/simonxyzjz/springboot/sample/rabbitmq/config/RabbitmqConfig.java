package simonxyzjz.springboot.sample.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(1)
@Component
public class RabbitmqConfig {
	
	@Autowired
	private RabbitmqProperties mqProperties;
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory);
	}
	
	@Bean(name="inboundQueue")
	public Queue inboundQueue() {
		return new Queue(mqProperties.getInboundQueue(), true, false, false);
	}
	
	@Bean  
    public DirectExchange directExchange() {  
        return new DirectExchange(mqProperties.getDirectExchangeName());  
    } 

	@Bean
	public Binding bindingInboundQueue(@Qualifier("inboundQueue") Queue inboundQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(inboundQueue).to(directExchange).with(mqProperties.getRoutingKey());
	}
}
