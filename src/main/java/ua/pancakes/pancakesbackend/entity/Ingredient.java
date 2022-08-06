package ua.pancakes.pancakesbackend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.validation.constraints.Positive;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class Ingredient extends BaseEntity {

    /** at Ukrainian */
    private String value;

    /** in UAH */
    @Positive
    private Integer price;

    /** in grams */
    @Positive
    private Integer weight;

    @Builder
    Ingredient(Long id, String value, Integer price, Integer weight) {
        super(id);
        this.value = value;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient ingredient)) return false;

        return Objects.equals(id, ingredient.id) &&
                Objects.equals(value, ingredient.value) &&
                Objects.equals(price, ingredient.price) &&
                Objects.equals(weight, ingredient.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), value, price, weight);
    }
}
