package ua.pancakes.pancakesbackend.repository;

import org.springframework.stereotype.Repository;
import ua.pancakes.pancakesbackend.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {
    Optional<Client> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
