package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private static String UPDATE_ADD_MEAL = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";

    public MealDao mealDao = new MealDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            mealDao.delete(Integer.parseInt(request.getParameter("id")));
            log.debug("forward to listMeal");
            forward = LIST_MEAL;
            request.setAttribute("meals", MealsUtil.getFilteredWithExcess(mealDao.readAll()
                    , LocalTime.MIN, LocalTime.MAX, 2000));
        } else if (action.equalsIgnoreCase("listMeal")) {
            request.setAttribute("meals", MealsUtil.getFilteredWithExcess(mealDao.readAll()
                    , LocalTime.MIN, LocalTime.MAX, 2000));
            log.debug("forward to meals");
            forward = LIST_MEAL;
        } else if (action.equalsIgnoreCase("update")) {
            Meal meal = mealDao.getMealById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("meal", meal);
            log.debug("forward to update Meal");
            forward = UPDATE_ADD_MEAL;
        } else {
            log.debug("forward to add Meal");
            forward = UPDATE_ADD_MEAL;
        }
        request.getRequestDispatcher(forward).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Meal meal = new Meal();
        meal.setDateTime(LocalDateTime.parse(request.getParameter("dateTime")));
        meal.setDescription(request.getParameter("description"));
        meal.setCalories(Integer.parseInt(request.getParameter("calories")));
        String mealid = request.getParameter("mealId");
        if (mealid == null || mealid.isEmpty()) {
            meal.setId(MealDaoImpl.counter.incrementAndGet());
            mealDao.addMeal(meal);
        } else {
            meal.setId(Integer.parseInt(mealid));
            mealDao.update(meal.getId(), meal);
        }
        request.setAttribute("meals", MealsUtil.getFilteredWithExcess(mealDao.readAll(), LocalTime.MIN, LocalTime.MAX, 2000));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
