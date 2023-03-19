package ru.mch.dreamjob.service;

import ru.mch.dreamjob.dto.FileDto;
import ru.mch.dreamjob.entity.File;

import java.util.Optional;

public interface FileService {

    File save(FileDto fileDto);

    Optional<FileDto> getFileById(int id);

    boolean deleteById(int id);
}