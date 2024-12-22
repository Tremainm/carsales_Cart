package ie.atu.carsalesapp_cart.MessageQueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "cart.queue";
    public static final String EXCHANGE_NAME = "carSalesExchange";

    @Bean
    public Queue cartQueue() {
        // Durable queue, not auto-delete
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue cartQueue, DirectExchange exchange) {
        return BindingBuilder.bind(cartQueue).to(exchange).with("cart.routing.key");
    }
}


