package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

public class AbstractMealController {
    private static final Logger log = LoggerFactory.getLogger(AbstractMealController.class);

    @Autowired
    private MealService service;

    public Meal save(Meal meal, Integer userId) {
        return service.save(meal, userId);
    }

    public void delete(int id, Integer userId) {
        service.delete(id, userId);
    }

    public Meal get(int id, Integer userId) {
        return service.get(id, userId);
    }

    public Collection<Meal> getAll(Integer userId) {
        return service.getAll(userId);
    }

}
