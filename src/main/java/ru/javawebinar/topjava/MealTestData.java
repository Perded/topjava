package ru.javawebinar.topjava;


import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ + 2;

    public static final Meal MEAL1 = new Meal(MEAL_ID, LocalDateTime.of(
            2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL_ID + 1, LocalDateTime.of(
            2015, Month.MAY, 30, 12, 0), "Обед", 1500);
    public static final Meal MEAL3 = new Meal(MEAL_ID + 2, LocalDateTime.of(
            2015, Month.MAY, 30, 10, 0), "Ужин", 1500);
    public static final Meal MEAL4 = new Meal(MEAL_ID + 3, LocalDateTime.of(
            2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL5 = new Meal(MEAL_ID + 4, LocalDateTime.of(
            2015, Month.MAY, 31, 12, 0), "Обед", 1000);
    public static final Meal MEAL6 = new Meal(MEAL_ID + 5, LocalDateTime.of(
            2015, Month.MAY, 31, 13, 0), "Обед", 2000);
}
