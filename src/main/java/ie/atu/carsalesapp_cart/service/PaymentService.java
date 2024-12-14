package ie.atu.carsalesapp_cart.service;

import ie.atu.carsalesapp_cart.entity.Payment;
import ie.atu.carsalesapp_cart.repository.CarRepository;
import ie.atu.carsalesapp_cart.repository.CartRepository;
import ie.atu.carsalesapp_cart.repository.PaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private CartRepository cartRepository;
    private CarRepository carRepository;

    //public Payment processPayment(int user_id, double amount){
    //    Payment payment = new Payment();
    //    payment.setUser_id(user_id);
    //    payment.setAmount(amount);

    //}
}
