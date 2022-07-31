package ua.pancakes.pancakesbackend.repository;

import org.springframework.stereotype.Repository;
import ua.pancakes.pancakesbackend.entity.Client;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {
}
