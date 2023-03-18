package ru.mch.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.mch.dreamjob.entity.Candidate;
import ru.mch.dreamjob.repository.CandidateRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleCandidateService implements CandidateService {

    private final CandidateRepository candidateRepository;

    public SimpleCandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate save(Candidate vacancy) {
        return candidateRepository.save(vacancy);
    }

    @Override
    public boolean deleteById(int id) {
        return candidateRepository.deleteById(id);
    }

    @Override
    public boolean update(Candidate vacancy) {
        return candidateRepository.update(vacancy);
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return candidateRepository.findById(id);
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidateRepository.findAll();
    }
}