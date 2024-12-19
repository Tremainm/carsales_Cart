package ie.atu.carsalesapp_cart.service;

import ie.atu.carsalesapp_cart.client.CarCartClient;
import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.repository.CartRepository;
import org.springframework.stereotype.Service;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CarCartClient carCartClient;

    public CartService(CartRepository cartRepository, CarCartClient carCartClient) {
        this.cartRepository = cartRepository;
        this.carCartClient = carCartClient;
    }

    public Cart addCarToCart(int carId) {
        Car car = carCartClient.getAllCars().stream()
                .filter(c -> c.getCar_id() == carId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Cart cart = new Cart();
        cart.setCar(car);
        //cart.setCarMake(cart.getMake());
        //cart.setCarModel(cart.getModel());
        //cart.setCarYear(cart.getYear());
        //cart.setCarCost(cart.getCost());

        return cartRepository.save(cart);
    }

    public double getTotalPrice() {
        return cartRepository.findAll().stream()
                .mapToDouble(cartEntity -> cartEntity.getCar().getCost())
                .sum();
    }
}
