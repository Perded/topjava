package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.meals;

public class MealDaoImpl implements MealDao {

    public static AtomicInteger counter = new AtomicInteger(0);

    private static final Logger log = getLogger(MealDaoImpl.class);

    @Override
    public Meal getMealById(int mealID) {
        log.debug("get meal by ID");
        return meals.get(mealID);
    }

    public static Integer getCounter() {
        return counter.get();
    }

    static {
        meals.put(counter.get(), new Meal(getCounter(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 100));
        meals.put(counter.incrementAndGet(), new Meal(getCounter(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        meals.put(counter.incrementAndGet(), new Meal(getCounter(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        meals.put(counter.incrementAndGet(), new Meal(getCounter(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        meals.put(counter.incrementAndGet(), new Meal(getCounter(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        meals.put(counter.incrementAndGet(), new Meal(getCounter(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }


    @Override
    public void addMeal(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> readAll() {
        log.debug("read ALL meal");
        return new ArrayList<>(meals.values());
    }

    @Override
    public void update(int id, Meal meal) {
        log.debug("update meal");
        meals.put(id, meal);
    }

    @Override
    public void delete(int id) {
        log.debug("delete meal");
        meals.remove(id);
    }
}
