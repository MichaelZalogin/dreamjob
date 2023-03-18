package ru.mch.dreamjob.service;

import ru.mch.dreamjob.entity.Vacancy;
import ru.mch.dreamjob.repository.MemoryVacancyRepository;
import ru.mch.dreamjob.repository.VacancyRepository;

import java.util.Collection;
import java.util.Optional;

public class SimpleVacancyService implements VacancyService {

    private static final SimpleVacancyService INSTANCE = new SimpleVacancyService();

    private final VacancyRepository vacancyRepository = MemoryVacancyRepository.getInstance();

    private SimpleVacancyService() {
    }

    public static SimpleVacancyService getInstance() {
        return INSTANCE;
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        return vacancyRepository.deleteById(id);
    }

    @Override
    public boolean update(Vacancy vacancy) {
        return vacancyRepository.update(vacancy);
    }

    @Override
    public Optional<Vacancy> findById(int id) {
        return vacancyRepository.findById(id);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }
}