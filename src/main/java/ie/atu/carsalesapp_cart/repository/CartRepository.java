package ie.atu.carsalesapp_cart.repository;

import ie.atu.carsalesapp_cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(int userId);
}
