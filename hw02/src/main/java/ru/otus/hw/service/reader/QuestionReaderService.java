package ru.otus.hw.service.reader;

import ru.otus.hw.dao.dto.QuestionDto;

import java.util.List;

public interface QuestionReaderService {

    List<QuestionDto> getQuestions(String sourceName, int skipLines);

}
