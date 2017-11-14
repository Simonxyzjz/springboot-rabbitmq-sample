package simonxyzjz.springboot.sample.rabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="rabbitmq-sample")
public class RabbitmqProperties {
	private String routingKey;
	private String directExchangeName;
	private String inboundQueue;
}
