package ie.atu.carsalesapp_cart.repository;

import ie.atu.carsalesapp_cart.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
