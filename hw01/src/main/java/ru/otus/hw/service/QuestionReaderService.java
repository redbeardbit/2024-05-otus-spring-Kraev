package ru.otus.hw.service;

import ru.otus.hw.dao.dto.QuestionDto;

import java.util.List;

public interface QuestionReaderService {

    public List<QuestionDto> getQuestions(String sourceName);

}
