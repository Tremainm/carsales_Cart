package ie.atu.carsalesapp_cart.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long car_id;

    @Column(name = "make")
    @NotBlank(message="make cannot be blank")
    private String make;

    @Column(name = "model")
    @NotBlank(message="model cannot be blank")
    private String model;

    @Column(name = "year")
    @Min(value = 1950,message = "car is too old")
    @Max(value =2025,message = "invalid year")
    private String year;

    @Column(name = "cost")
    @NotNull(message="cost cannot be 0")
    private double cost;

}
