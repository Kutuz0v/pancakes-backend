package ua.pancakes.pancakesbackend.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class Client extends BaseEntity {

    @NotBlank(message = "Name cannot be blank")
    @Length(min = 2,
            message = "Name must be at least 2 characters long")
    @Length(max = 50,
    message = "Name cannot be more than 50 characters")
    private String name;

    // TODO: validate phoneNumber
    private String phoneNumber;

    @Builder
    public Client(Long id, String name, String phoneNumber) {
        super(id);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        //if (!super.equals(o)) return false;

        return Objects.equals(id, client.id) &&
                Objects.equals(name, client.name) &&
                Objects.equals(phoneNumber, client.phoneNumber);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = name == null ? result : result * 31 + name.hashCode();
        result = phoneNumber == null ? result : result * 31 + phoneNumber.hashCode();
        return result;
    }
}
