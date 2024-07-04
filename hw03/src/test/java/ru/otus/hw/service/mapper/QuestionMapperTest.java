package ru.otus.hw.service.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw.dao.dto.QuestionDto;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("QuestionMapperTest | map DTO to Question ")
class QuestionMapperTest {

    private QuestionMapper questionMapper;

    private QuestionDto questionDto;
    private List<QuestionDto> questionDtoList;


    @BeforeEach
    public void init() {
        questionMapper = new QuestionMapper();
        questionDto = new QuestionDto();
        questionDtoList = new ArrayList();

        questionDto.setText("it's okay?");
        questionDto.setAnswers(Arrays.asList(
                new Answer("Science doesn't know this yet", true),
                new Answer("maybe yes", false),
                new Answer("maybe no", false)
        ));

        questionDtoList.add(questionDto);
    }

    @Test
    void questionDtosToQuestions_NullQuestionDto_ShouldMapOnlyNonNullAnswers() {

        questionDtoList.add(null);

        List<Question> questions = questionMapper.questionDtosToQuestions(questionDtoList);

        assertThat(questions).isNotEmpty().size().isEqualTo(1);
    }

    @Test
    void questionDtosToQuestions_correctQuestionDto_ShouldMapCorrectToAnswers() {

        List<Question> expectedQuestions = List.of(
                new Question("it's okay?", Arrays.asList(
                        new Answer("Science doesn't know this yet", true),
                        new Answer("maybe yes", false),
                        new Answer("maybe no", false)
                )
                ));

        List<Question> questions = questionMapper.questionDtosToQuestions(questionDtoList);

        Assertions.assertThat(expectedQuestions).hasSameElementsAs(questions);

    }

}
