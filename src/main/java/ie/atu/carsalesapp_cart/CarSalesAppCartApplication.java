package ie.atu.carsalesapp_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CarSalesAppCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarSalesAppCartApplication.class, args);
    }

}
