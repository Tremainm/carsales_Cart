package ie.atu.carsalesapp_cart.controller;

import ie.atu.carsalesapp_cart.client.CarCartClient;
import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.service.CartService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CarCartClient carCartClient;

    public CartController(CartService cartService, CarCartClient carCartClient) {
        this.cartService = cartService;
        this.carCartClient = carCartClient;
    }

    @PostMapping("/add/{car_id}")
    public Cart addCarToCart(@PathVariable Long car_id) {
        return cartService.addCarToCart(car_id);
    }

    @GetMapping("/total")
    public double getTotalPrice() {
        return cartService.getTotalPrice();
    }

    @GetMapping("/allcars")
    public List<Car> getAllCarsFromCarService() {
        return carCartClient.getAllCars();
    }
    @GetMapping("/car/{id}")
    public Car getCarById(@PathVariable Long id){
        return carCartClient.getCarById(id);
    }
}