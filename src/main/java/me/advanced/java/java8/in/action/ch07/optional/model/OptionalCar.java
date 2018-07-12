package me.advanced.java.java8.in.action.ch07.optional.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Created by taesu on 2018-07-12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionalCar {
    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance == null ? Optional.empty() : insurance;
    }
}
