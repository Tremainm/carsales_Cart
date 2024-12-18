package ie.atu.carsalesapp_cart.controller;

import ie.atu.carsalesapp_cart.client.CarCartClient;
import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final CarCartClient carCartClient;

    public CartController(CartService cartService, CarCartClient carCartClient) {
        this.cartService = cartService;
        this.carCartClient = carCartClient;
    }

    @PostMapping
    public String makeCart(@RequestBody Car car)
    {
        return "Microservice on port 8081 called";
    }

    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody Car car, @RequestParam int userId)
    {
        cartService.addCarToCart(userId, car);
        return ResponseEntity.ok("Car added to cart");
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable int userId)
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
