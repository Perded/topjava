package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;

import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    public MealService service;

    @Test
    public void get() throws Exception {
        Meal meal = service.get(100004, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        Meal meal = service.get(0, USER_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(100003, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        service.delete(100003, ADMIN_ID);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        service.getBetweenDateTimes(LocalDateTime.of(2015, Month.JUNE, 1, 0, 0)
                , LocalDateTime.of(2019, Month.JUNE, 1, 23, 59), USER_ID);
    }

    @Test
    public void getAll() throws Exception {
        service.getAll(USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotFound() throws Exception {
        service.update(new Meal(0, LocalDateTime.of(2016, Month.JUNE, 1, 0, 0)
                , "Fig znaet chto el", 1300), USER_ID);
    }

    @Test
    public void update() throws Exception {
        service.update(new Meal(100002, LocalDateTime.of(2016, Month.JUNE, 1, 0, 0)
                , "Fig znaet chto el", 1300), USER_ID);
    }

    @Test
    public void create() throws Exception {
        service.create(new Meal(LocalDateTime.of(2016, Month.JUNE, 1, 0, 0)
                , "Fig znaet chto el", 1900), ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotMyMeal() throws Exception {
        Meal meal = service.get(100004, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void updateNotMyMeal() throws Exception {
        service.update(new Meal(100004, LocalDateTime.of(2016, Month.JUNE, 1, 0, 0)
                , "Fig znaet chto el", 1300), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotMyMeal() throws Exception {
        service.delete(100002, ADMIN_ID);
    }

}