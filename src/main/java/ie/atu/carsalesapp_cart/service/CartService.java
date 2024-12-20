package ie.atu.carsalesapp_cart.service;

import ie.atu.carsalesapp_cart.client.CarCartClient;
import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CarCartClient carCartClient;

    public CartService(CartRepository cartRepository, CarCartClient carCartClient) {
        this.cartRepository = cartRepository;
        this.carCartClient = carCartClient;
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Transactional
    public String removeFromCart(Long cart_id) {
        List<Cart> carsToRemove = cartRepository.findAll().stream()
                .filter(cart -> cart.getCart_id().equals(cart_id))
                .toList();

        if(carsToRemove.isEmpty())
        {
            throw new RuntimeException("Car with cart ID " + cart_id + " not found in cart");
        }
        cartRepository.deleteAll(carsToRemove);

        return "Car with cart ID " + cart_id + " removed from car cart";
    }

    @Transactional
    public Cart addCarToCart(Long car_id) {
        Car car = carCartClient.getAllCars().stream()
                .filter(c -> c.getCar_id() == car_id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Car not found"));

        Cart cart = new Cart();
        cart.setCar_id(car.getCar_id());
        cart.setMake(car.getMake());
        cart.setModel(car.getModel());
        cart.setYear(car.getYear());
        cart.setCost(car.getCost());

        return cartRepository.save(cart);
    }

    public double getTotalPrice() {
        return cartRepository.findAll().stream()
                .mapToDouble(Cart::getCost)
                .sum();
    }
}
