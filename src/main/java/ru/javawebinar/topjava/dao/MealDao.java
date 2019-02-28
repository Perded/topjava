package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealDao {
    Meal getMealById(int mealID);
    void addMeal(Meal meal);
    List<Meal> readAll();
    void update(int id, Meal meal);
    void delete(int id);
}
