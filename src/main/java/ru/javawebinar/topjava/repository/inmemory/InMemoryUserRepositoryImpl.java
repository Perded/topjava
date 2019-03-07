package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private Map<Integer, User> repository = new ConcurrentHashMap();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    {
        repository.put(counter.get(), new User(0, "Admin", "admin@mail.ru", "admin", Role.ROLE_ADMIN));
        repository.put(counter.incrementAndGet(), new User(1, "User", "user@mail.ru", "user", Role.ROLE_USER));
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        if (repository.get(id) != null) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);
        return repository.put(counter.incrementAndGet(), user);
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return repository.values().stream().sorted(Comparator.comparing(AbstractNamedEntity::getName))
                .collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return repository.values().stream().filter(u -> u.getEmail().equals(email))
                .collect(Collectors.toList()).get(0);
    }
}
