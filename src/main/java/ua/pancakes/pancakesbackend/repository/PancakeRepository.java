package ua.pancakes.pancakesbackend.repository;

import org.springframework.stereotype.Repository;
import ua.pancakes.pancakesbackend.entity.Pancake;

@Repository
public interface PancakeRepository extends BaseRepository<Pancake, Long> {
}
