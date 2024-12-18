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

    @GetMapping("/items")
    public ResponseEntity<Map<String, Object>> getCartItems(@RequestParam Long userId) {
        List<Car> cars = cartService.getCarsInCart(userId);
        double totalCost = cartService.calculateTotalCost(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("userId", userId);
        response.put("cartItems", cars);
        response.put("total", totalCost);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public String makeCart(@RequestBody Car car)
    {
        return "Microservice on port 8081 called";
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@Valid @RequestBody Car car, @RequestParam int userId)
    {
        cartService.addCarToCart(userId, car);
        return ResponseEntity.ok("Car added to cart");
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<Cart> getCart(@Valid @PathVariable int userId)
    {
        Cart cart = cartService.getCart(userId);
        return ResponseEntity.ok(cart);
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
