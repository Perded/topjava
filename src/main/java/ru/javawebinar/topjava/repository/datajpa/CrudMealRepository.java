package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    Meal getByIdAndUserId(Integer id, Integer userId);

    @Modifying
    @Transactional
    @Query("UPDATE Meal m SET m.dateTime = ?1, m.calories= ?2," +
            "m.description=?3 where m.id=?4 and m.user.id=?5")
    int saveMeal(LocalDateTime dateTime, int calories, String description, Integer id, int userId);

    @Modifying
    @Transactional
    int deleteByIdAndUserId(int id, int userId);

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);

    List<Meal> findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);
}
