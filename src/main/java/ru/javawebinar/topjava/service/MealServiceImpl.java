package ru.javawebinar.topjava.service;

import org.omg.CORBA.NO_IMPLEMENT;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Meal meal, Integer userId) throws NotFoundException {
        if (meal == null) throw new NotFoundException("Meal is null");
        else if (!meal.getUserId().equals(userId)) throw new NotFoundException("Diff userId");
        else repository.save(meal);
        return null;
    }

    @Override
    public void delete(int id, Integer userId) throws NotFoundException {
        Meal meal = repository.get(id);
        if (meal == null) throw new NotFoundException("Meal is null");
        else if (!meal.getUserId().equals(userId)) throw new NotFoundException("Diff userId");
            else repository.delete(id);
    }

    @Override
    public Meal get(int id, Integer userId) throws NotFoundException {
        Meal meal = repository.get(id);
        if (meal == null) new NotFoundException("Meal is null");
        else if (!meal.getUserId().equals(userId)) new NotFoundException("Diff userId");
        return meal;

    }

    @Override
    public Collection<Meal> getAll(Integer userId) {
        return repository.getAll(userId);
    }
}