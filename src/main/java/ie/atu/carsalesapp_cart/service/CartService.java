package ie.atu.carsalesapp_cart.service;

import ie.atu.carsalesapp_cart.entity.Car;
import ie.atu.carsalesapp_cart.entity.Cart;
import ie.atu.carsalesapp_cart.repository.CarRepository;
import ie.atu.carsalesapp_cart.repository.CartRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class CartService {

    private  CartRepository cartRepository;
    private CarRepository carRepository;

    public Cart addToCart(int user_id, int car_id, int quantity)
    {
        Optional<Car> car = carRepository.findById((long)car_id);
        Cart cart = new Cart();
        cart.setUserId(user_id);
        cart.setCarId(car_id);
        cart.setQuantity(quantity);

        Cart existingCart = cartRepository.findByUserIdAndCarId(user_id, car_id);
        if(existingCart != null){
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            return cartRepository.save(existingCart);
        }
        return cartRepository.save(cart);
    }

    public double calcTotal(int user_id){
        double total = 0;
        List<Cart> cartItem = cartRepository.findByUserId(user_id);
        for(Cart c : cartItem){
            Car car = carRepository.findById((long) c.getCarId()).orElse(null);
            if(car != null)
            {
                total += car.getCost() * c.getQuantity();
            }
        }
        return total;
    }

    public void removeFromCart(int user_id, int car_id){
        Cart cart = cartRepository.findByUserIdAndCarId(user_id, car_id);
        if(cart != null){
            cartRepository.delete(cart);
        }
    }
}
