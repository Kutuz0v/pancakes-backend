package ua.pancakes.pancakesbackend.repository;

import org.springframework.stereotype.Repository;
import ua.pancakes.pancakesbackend.model.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {
    Boolean existsByEmail(String email);

    Optional<Client> findByEmail(String email);
}
