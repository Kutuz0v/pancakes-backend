package ua.pancakes.pancakesbackend.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
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
public class Pancake extends BaseEntity{

    /** in UAH */
    @Positive
    private Integer price;

    /** in grams */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Pancake pancake = (Pancake) o;
        return id != null && Objects.equals(id, pancake.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
