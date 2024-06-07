package ru.otus.hw.dao;

import ru.otus.hw.dao.dto.QuestionDto;

import java.util.List;

public interface QuestionReader {

    public List<QuestionDto> getQuestions(String sourceName);

}
