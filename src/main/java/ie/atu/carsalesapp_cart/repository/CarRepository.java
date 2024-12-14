package ie.atu.carsalesapp_cart.repository;

import ie.atu.carsalesapp_cart.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
