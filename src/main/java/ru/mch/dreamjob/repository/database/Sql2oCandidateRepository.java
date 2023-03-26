package ru.mch.dreamjob.repository.database;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.mch.dreamjob.entity.Candidate;
import ru.mch.dreamjob.repository.CandidateRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class Sql2oCandidateRepository implements CandidateRepository {

    private final Sql2o sql2o;

    public Sql2oCandidateRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Candidate save(Candidate candidate) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("""
                    INSERT INTO candidate (name, description, creation_date, city_id, file_id)
                    VALUES (:name, :description, :creation_date, :city_id, :file_id)
                    """, true);
            query.addParameter("name", candidate.getName());
            query.addParameter("description", candidate.getDescription());
            query.addParameter("creation_date", candidate.getCreationDate());
            query.addParameter("city_id", candidate.getCityId());
            query.addParameter("file_id", candidate.getFileId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            candidate.setId(generatedId);
        }
        return candidate;
    }

    @Override
    public boolean deleteById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("""
                    DELETE FROM candidate WHERE id = :id
                    """);
            query.addParameter("id", id);
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public boolean update(Candidate candidate) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("""
                    UPDATE candidate
                    SET name = :name, description = :description,
                     creation_date = :creation_date, city_id = :city_id, file_id = :city_id
                    WHERE id = :id
                    """);
            query.addParameter("name", candidate.getName());
            query.addParameter("description", candidate.getDescription());
            query.addParameter("creation_date", candidate.getCreationDate());
            query.addParameter("city_id", candidate.getCityId());
            query.addParameter("file_id", candidate.getFileId());
            query.addParameter("id", candidate.getId());
            var affectedRows = query.executeUpdate().getResult();
            return affectedRows > 0;
        }
    }

    @Override
    public Optional<Candidate> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("""
                                SELECT *
                                FROM candidate
                                WHERE id = :id
                    """);
            query.addParameter("id", id);
            var candidate = query.setColumnMappings(Candidate.COLUMN_MAPPING).executeAndFetchFirst(Candidate.class);
            return Optional.ofNullable(candidate);
        }
    }

    @Override
    public Collection<Candidate> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM candidate");
            return query.setColumnMappings(Candidate.COLUMN_MAPPING).executeAndFetch(Candidate.class);
        }
    }
}
