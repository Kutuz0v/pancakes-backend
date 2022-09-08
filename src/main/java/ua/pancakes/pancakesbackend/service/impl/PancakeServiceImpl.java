package ua.pancakes.pancakesbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.pancakes.pancakesbackend.model.Ingredient;
import ua.pancakes.pancakesbackend.model.Pancake;
import ua.pancakes.pancakesbackend.repository.PancakeRepository;
import ua.pancakes.pancakesbackend.service.ClientService;
import ua.pancakes.pancakesbackend.service.PancakeService;
import ua.pancakes.pancakesbackend.service.security.UserDetailsImpl;

import java.util.List;

@Service
public class PancakeServiceImpl
        extends BaseServiceImpl<Pancake>
        implements PancakeService {

    @Autowired
    ClientService clientService;

    @Autowired
    PancakeRepository repository;

    @Override
    public Pancake create(Pancake pancake) {
        Integer price = 0, weight = 0;
        for (Ingredient ingredient : pancake.getIngredients()) {
            price += ingredient.getPrice();
            weight += ingredient.getWeight();
        }
        if (!pancake.getPrice().equals(price) ||
                !pancake.getWeight().equals(weight) ||
                pancake.getIngredients().size() > 5)
            throw new IllegalArgumentException("Bad ingredients");

        pancake.setClient(
                clientService.get(
                        ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                                .getId()
                )
        );
        return super.create(pancake);
    }

    @Override
    public List<Pancake> getAllForCurrentClient() {
        Long currentClientId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .getId();
        return repository.findPancakesByClient_Id(currentClientId);
    }
}
