package ie.atu.carsalesapp_cart.service;

import ie.atu.carsalesapp_cart.client.CarCartClient;
import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CarCartClient carCartClient;

    public CartService(CartRepository cartRepository, CarCartClient carCartClient) {
        this.cartRepository = cartRepository;
        this.carCartClient = carCartClient;
    }

    @Transactional
    public Cart addCarToCart(Long car_id) {
        Car car = carCartClient.getAllCars().stream()
                .filter(c -> c.getCar_id() == car_id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Cart cart = new Cart();
        cart.setCar_id(car.getCar_id());
        cart.setCarMake(car.getMake());
        cart.setCarModel(car.getModel());
        cart.setCarYear(car.getYear());
        cart.setCarCost(car.getCost());

        return cartRepository.save(cart);
    }

    public double getTotalPrice() {
        return cartRepository.findAll().stream()
                .mapToDouble(Cart::getCarCost)
                .sum();
    }
}
