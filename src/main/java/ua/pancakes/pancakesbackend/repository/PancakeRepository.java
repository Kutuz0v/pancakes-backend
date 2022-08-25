package ua.pancakes.pancakesbackend.repository;

import org.springframework.stereotype.Repository;
import ua.pancakes.pancakesbackend.model.Pancake;

import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Repository
public interface PancakeRepository extends BaseRepository<Pancake, Long> {
    List<Pancake> findPancakesByClient_Id(@PositiveOrZero Long client_id);
}
