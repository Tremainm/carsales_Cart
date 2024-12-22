package ie.atu.carsalesapp_cart.MessageQueue;

import ie.atu.carsalesapp_cart.service.CartService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final CartService cartService;

    public MessageConsumer(CartService cartService) {
        this.cartService = cartService;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void consumeMessage(String message) {
        System.out.println("Message received: " + message);

        // Parse message and process it
        String[] parts = message.split(",");
        Long carId = Long.parseLong(parts[0].split("=")[1].trim());
        String action = parts[1].split("=")[1].trim();

        if ("ADD".equalsIgnoreCase(action)) {
            cartService.addCarToCart(carId);
        } else if ("REMOVE".equalsIgnoreCase(action)) {
            cartService.removeFromCart(carId);
        } else {
            System.out.println("Unknown action: " + action);
        }
    }
}

