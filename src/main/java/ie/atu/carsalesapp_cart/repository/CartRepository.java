package ie.atu.carsalesapp_cart.repository;

import ie.atu.carsalesapp_cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserIdAndCarId(int user_id, int car_id);
    List<Cart> findByUserId(int user_id);
}
