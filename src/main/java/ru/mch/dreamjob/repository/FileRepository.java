package ru.mch.dreamjob.repository;

import ru.mch.dreamjob.entity.File;

import java.util.Optional;

public interface FileRepository {

    File save(File file);

    Optional<File> findById(int id);

    boolean deleteById(int id);
}