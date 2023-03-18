package ru.mch.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.mch.dreamjob.entity.City;
import ru.mch.dreamjob.repository.CityRepository;

import java.util.Collection;

@Service
public class SimpleCityService implements CityService {

    private final CityRepository cityRepository;

    public SimpleCityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public Collection<City> findAll() {
        return cityRepository.findAll();
    }
}