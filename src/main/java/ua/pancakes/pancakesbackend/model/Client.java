package ua.pancakes.pancakesbackend.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

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
    // TODO: refactor to 2 fields name
    private String username;

    // TODO: validate phoneNumber
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Cascade(SAVE_UPDATE)
    @ManyToMany()
    @JoinTable(name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @Builder
    public Client(Long id, String username, String email, String password, Set<Role> roles) {
        super(id);
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;

        return Objects.equals(id, client.id) &&
                Objects.equals(username, client.username) &&
                Objects.equals(email, client.email) &&
                Objects.equals(password, client.password) &&
                Objects.equals(roles, client.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, password, roles);
    }
}
