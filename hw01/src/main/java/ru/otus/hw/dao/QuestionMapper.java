package ru.otus.hw.dao;

import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionMapper {

    public List<Question> questionDtosToQuestions(List<QuestionDto> questionDtoList) {
        return questionDtoList.stream()
                .filter(Objects::nonNull)
                .map(dto -> dto.toDomainObject())
                .collect(Collectors.toList());
    }

}
