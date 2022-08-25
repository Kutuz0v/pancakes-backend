package ua.pancakes.pancakesbackend.service;

import ua.pancakes.pancakesbackend.model.Pancake;

import java.util.List;

public interface PancakeService extends BaseService<Pancake> {
    List<Pancake> getAllForCurrentClient();
}
