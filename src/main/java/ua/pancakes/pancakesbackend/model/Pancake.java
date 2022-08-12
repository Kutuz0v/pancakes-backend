package ua.pancakes.pancakesbackend.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Positive;
import java.util.Objects;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Pancake extends BaseEntity {

    /**
     * in UAH
     */
    @Positive
    private Integer price;

    /**
     * in grams
     */
    @Positive
    private Integer weight;

    @Cascade(SAVE_UPDATE)
    @ManyToMany()
    @JoinTable(
            name = "related_ingredients",
            joinColumns = @JoinColumn(
                    name = "pancake_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ingredient_id",
                    referencedColumnName = "id"
            )
    )
    @ToString.Exclude
    private Set<Ingredient> ingredients;

    @Builder
    Pancake(Integer price, Integer weight, Set<Ingredient> ingredients) {
        this.price = price;
        this.weight = weight;
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pancake pancake)) return false;

        return Objects.equals(id, pancake.id) &&
                Objects.equals(price, pancake.price) &&
                Objects.equals(weight, pancake.weight) &&
                Objects.equals(ingredients, pancake.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, weight, ingredients);
    }
}
