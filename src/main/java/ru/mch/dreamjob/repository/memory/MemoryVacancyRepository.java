package ru.mch.dreamjob.repository.memory;

import org.springframework.stereotype.Repository;
import ru.mch.dreamjob.entity.Vacancy;
import ru.mch.dreamjob.repository.VacancyRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryVacancyRepository implements VacancyRepository {

    private int nextId = 1;

    private final Map<Integer, Vacancy> vacancies = new HashMap<>();

    private MemoryVacancyRepository() {
        save(new Vacancy(0, "Intern Java Developer", "description", false, 1, 0));
        save(new Vacancy(0, "Junior Java Developer", "description", false, 1, 0));
        save(new Vacancy(0, "Junior+ Java Developer", "description", false, 1, 0));
        save(new Vacancy(0, "Middle Java Developer", "description", false, 2, 0));
        save(new Vacancy(0, "Middle+ Java Developer", "description", false, 2, 0));
        save(new Vacancy(0, "Senior Java Developer", "description", false, 3, 0));
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        vacancy.setId(nextId++);
        vacancies.put(vacancy.getId(), vacancy);
        return vacancy;
    }

    @Override
    public boolean deleteById(int id) {
        return vacancies.remove(id) != null;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancies.computeIfPresent(vacancy.getId(),
                (id, oldVacancy) -> new Vacancy(
                        oldVacancy.getId(),
                        vacancy.getTitle(),
                        vacancy.getDescription(),
                        vacancy.getVisible(),
                        vacancy.getCityId(),
                        vacancy.getFileId()))
               != null;
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return Optional.ofNullable(vacancies.get(id));
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancies.values();
    }
}