package ru.mch.dreamjob.repository;

import ru.mch.dreamjob.entity.City;
import java.util.Collection;

public interface CityRepository {
    Collection<City> findAll();
}