package ua.pancakes.pancakesbackend.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;
import ua.pancakes.pancakesbackend.model.Pancake;
import ua.pancakes.pancakesbackend.model.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.hibernate.annotations.CascadeType.SAVE_UPDATE;

@Getter
@Setter
@ToString(callSuper = true)
@RequiredArgsConstructor
public class ClientDto {

    // TODO: validate phoneNumber
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    @JsonIgnore
    private String password;

    @NotBlank(message = "First name cannot be blank")
    @Length(min = 2,
            message = "First name must be at least 2 characters long")
    @Length(max = 50,
            message = "First name cannot be more than 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Length(min = 2,
            message = "Last name must be at least 2 characters long")
    @Length(max = 50,
            message = "Last name cannot be more than 50 characters")
    private String lastName;

    @Cascade(SAVE_UPDATE)
    @ManyToMany()
    @JoinTable(name = "client_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Pancake> pancakes;

    @Builder
    public ClientDto(String firstName, String lastName, String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDto client)) return false;

        return Objects.equals(email, client.email) &&
                Objects.equals(password, client.password) &&
                Objects.equals(roles, client.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, password, roles);
    }
}
