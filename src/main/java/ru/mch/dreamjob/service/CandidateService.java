package ru.mch.dreamjob.service;

import ru.mch.dreamjob.dto.FileDto;
import ru.mch.dreamjob.entity.Candidate;

import java.util.Collection;
import java.util.Optional;

public interface CandidateService {

    Candidate save(Candidate vacancy, FileDto image);

    boolean deleteById(int id);

    boolean update(Candidate vacancy, FileDto image);

    Optional<Candidate> findById(int id);

    Collection<Candidate> findAll();
}