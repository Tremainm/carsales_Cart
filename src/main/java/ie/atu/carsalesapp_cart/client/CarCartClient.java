package ie.atu.carsalesapp_cart.client;

import ie.atu.carsalesapp_cart.entity.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="car",url ="http://localhost:8080/cars")
public interface CarCartClient {
    @GetMapping("/allcars")
    List<Car> getAllCars() ;

    @GetMapping("/cars/{id}")
    Car getCarById(@PathVariable("id") Long id);
}
