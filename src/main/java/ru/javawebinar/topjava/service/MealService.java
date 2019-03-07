package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

public interface MealService {
    Meal save(Meal meal, Integer userID) throws NotFoundException;

    void delete(int id, Integer userID) throws NotFoundException;

    Meal get(int id, Integer userId) throws NotFoundException;

    Collection<Meal> getAll(Integer userId) throws NotFoundException;
}