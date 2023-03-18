package ru.mch.dreamjob.service;

import ru.mch.dreamjob.entity.City;
import java.util.Collection;

public interface CityService {
    Collection<City> findAll();
}