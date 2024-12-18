package ie.atu.carsalesapp_cart.service;

import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private final CartRepository cartRepository;

    //private final Map<Integer, Cart> userCarts = new HashMap<>();

    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }

    public List<Car> getCarsInCart(Long userId){
        return cartRepository.findByUserId(Math.toIntExact(userId)).map(Cart::getCars).orElseThrow(() -> new RuntimeException("Cart not found for user: " + userId));
    }

    public double calculateTotalCost(Long userId) {
        return getCarsInCart(userId)
                .stream()
                .mapToDouble(Car::getCost)
                .sum();
    }

    public Cart getCart(int userId)
    {
        //return userCarts.getOrDefault(userId, new Cart());

        return cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUserId(userId);
                    return cartRepository.save(newCart); // Save new cart and return it
                });
    }

    public void addCarToCart(int userId, Car car)
    {
        Cart cart = getCart(userId);
        cart.addCar(car);
        cartRepository.save(cart);

        //userCarts.put(userId, cart);
    }

    public Cart getCarById(long cartId)
    {
        return cartRepository.findById(cartId).orElseThrow(()-> new RuntimeException("Cart not found"));
    }
}
