package ie.atu.carsalesapp_cart.repository;

import ie.atu.carsalesapp_cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
