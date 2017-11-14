package simonxyzjz.springboot.sample.rabbitmq.message;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InboundMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String payload;
}
