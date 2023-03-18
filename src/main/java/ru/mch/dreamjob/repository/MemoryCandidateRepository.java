package ru.mch.dreamjob.repository;

import org.springframework.stereotype.Repository;
import ru.mch.dreamjob.entity.Candidate;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoryCandidateRepository implements CandidateRepository {

    private int nextId = 1;

    private final Map<Integer, Candidate> candidates = new HashMap<>();

    private MemoryCandidateRepository() {
        save(new Candidate(0, "Ivan", "description", 1));
        save(new Candidate(0, "Petr", "description", 2));
        save(new Candidate(0, "Michael", "description", 2));
        save(new Candidate(0, "Vladimir", "description", 3));
        save(new Candidate(0, "Pavel", "description", 2));
    }

    @Override
    public Candidate save(Candidate candidate) {
        candidate.setId(nextId++);
        candidates.put(candidate.getId(), candidate);
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        return candidates.remove(id) != null;
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidates.computeIfPresent(candidate.getId(),
                (id, oldCandidates) -> new Candidate(
                        oldCandidates.getId(),
                        candidate.getName(),
                        candidate.getDescription(),
                        candidate.getCityId()
                )) != null;
    }

    @Override
    public Optional<Candidate> findById(int id) {
        return Optional.ofNullable(candidates.get(id));
    }

    @Override
    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}