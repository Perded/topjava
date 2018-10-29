package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import javax.jws.soap.SOAPBinding;
import javax.rmi.CORBA.Util;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<UserMealWithExceed> list = getFilteredWithExceededOptional2(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        for (UserMealWithExceed mealWithExceed : list) {
            System.out.println(mealWithExceed);
        }
    }

    public static List<UserMealWithExceed> getFilteredWithExceededOptional2(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> countCalories = mealList.stream()
                .collect(Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));

        return mealList.stream()
                .filter(meal -> TimeUtil.isBetween(meal.getTime(), startTime, endTime))
                .map(meal -> createWithExceed(meal, countCalories.get(meal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<UserMealWithExceed> getFilteredWithExceededOptional(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> countCalories = new HashMap<>();
        mealList.forEach(meal -> countCalories.put(meal.getDateTime().toLocalDate(),
                (countCalories.get(meal.getDateTime().toLocalDate()) != null ?
                        countCalories.get(meal.getDateTime().toLocalDate()) : 0) + meal.getCalories()));

        List<UserMealWithExceed> meals = new ArrayList<>();
        mealList.forEach(meal -> {
            if (TimeUtil.isBetween(meal.getDateTime().toLocalTime(), startTime, endTime) == true)
                meals.add(new UserMealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        countCalories.get(meal.getDateTime().toLocalDate()) > caloriesPerDay ? true : false));
        });
        return meals;
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        Map<LocalDate, Integer> countCalories = new HashMap<>();
        for (UserMeal meal : mealList) {
            LocalDate localDate = meal.getDateTime().toLocalDate();
            countCalories.put(localDate, (countCalories.get(localDate) != null ?
                    countCalories.get(localDate) : 0) + meal.getCalories());
        }

        List<UserMealWithExceed> meals = new ArrayList<>();
        for (UserMeal meal : mealList) {
            LocalDateTime dateTime = meal.getDateTime();
            if (TimeUtil.isBetween(dateTime.toLocalTime(), startTime, endTime) == true)
                meals.add(new UserMealWithExceed(meal.getId(), dateTime, meal.getDescription(), meal.getCalories(),
                        countCalories.get(dateTime.toLocalDate()) > caloriesPerDay ? true : false));
        }
        return meals;
    }

    public static UserMealWithExceed createWithExceed(UserMeal meal, boolean exceeded) {
        return new UserMealWithExceed(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories(), exceeded);
    }

}
