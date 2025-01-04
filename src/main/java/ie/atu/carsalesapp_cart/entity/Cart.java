package ie.atu.carsalesapp_cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long cart_id;

    @Column(name = "carMake")
    private String make;

    @Column(name = "carModel")
    private String model;

    @Column(name = "carYear")
    private String year;

    @Column(name = "carCost")
    private double cost;

    private Long car_id;

}

