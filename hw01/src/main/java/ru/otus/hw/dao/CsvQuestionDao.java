package ru.otus.hw.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;
import ru.otus.hw.service.reader.QuestionReaderService;
import ru.otus.hw.service.mapper.QuestionMapper;


import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    private final QuestionMapper questionMapper;

    private final QuestionReaderService questionReaderService;

    public List<Question> findAll() {

        List<QuestionDto> questionDtoList = questionReaderService.getQuestions(fileNameProvider.getTestFileName());

        return questionMapper.questionDtosToQuestions(questionDtoList);
    }

}
