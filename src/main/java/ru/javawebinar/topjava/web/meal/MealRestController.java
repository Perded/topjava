package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collection;

import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController extends AbstractMealController {

    private MealService service;

    public Meal save(Meal meal, Integer userId) {
        return super.save(meal, authUserId());
    }

    public void delete(int id, Integer userId) {
        super.delete(id, authUserId());
    }

    public Meal get(int id, Integer userId) {
        return super.get(id, authUserId());
    }

    public Collection<Meal> getAll(Integer userId) {
        return super.getAll(authUserId());
    }


}