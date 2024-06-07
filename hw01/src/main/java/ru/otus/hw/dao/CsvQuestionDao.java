package ru.otus.hw.dao;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.config.TestFileNameProvider;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;


import java.util.List;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider fileNameProvider;

    private final QuestionMapper questionMapper;

    private final QuestionReader questionReader;

    public List<Question> findAll() {

        List<QuestionDto> questionDtoList = questionReader.getQuestions(fileNameProvider.getTestFileName());

        return questionMapper.questionDtosToQuestions(questionDtoList);
    }

}
