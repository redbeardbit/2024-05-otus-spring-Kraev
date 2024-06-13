package ru.otus.hw.service.mapper;

import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Question;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuestionMapper {

    public List<Question> questionDtosToQuestions(List<QuestionDto> questionDtoList) {
        return questionDtoList.stream()
                .filter(Objects::nonNull)
                .map(QuestionDto::toDomainObject)
                .collect(Collectors.toList());
    }

}
