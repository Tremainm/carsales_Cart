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

    //@PostMapping
    //public String makeCart(@RequestBody Car car)
    //{
      //  return "Microservice on port 8081 called";
    //}

    @PostMapping("/add")
    public Cart addCarToCart(@RequestParam int car_id, @RequestParam int user_id, @RequestParam int quantity)
    {
        return cartService.addToCart(user_id, car_id, quantity);
    }

    @GetMapping
    public double getTotal(@RequestParam int user_id)
    {
        return cartService.calcTotal(user_id);
    }

    @DeleteMapping
    public void removeCarFromCart(@RequestParam int user_id, @RequestParam int car_id)
    {
        cartService.removeFromCart(user_id, car_id);
    }

}
