package ua.pancakes.pancakesbackend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;

        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name);
    }
}
