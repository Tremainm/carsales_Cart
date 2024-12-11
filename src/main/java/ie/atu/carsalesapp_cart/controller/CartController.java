package ie.atu.carsalesapp_cart.controller;

import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
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

}
