package ru.otus.hw.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.dao.QuestionDao;
import ru.otus.hw.domain.Answer;
import ru.otus.hw.domain.Question;
import ru.otus.hw.domain.Student;
import ru.otus.hw.domain.TestResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        questions.forEach(q -> {
            askStudentQuestion(q);
            testResult.applyAnswer(q, isAnswerValid(q.answers(), getStudentAnswer(q.answers().size())));
        });

        return testResult;
    }

    private void askStudentQuestion(final Question question) {

        ioService.printFormattedLine("Q:\"%s\"", question.text());

        int answerNumber = 0;

        for (var answer : question.answers()) {
            answerNumber = answerNumber + 1;
            ioService.printFormattedLine(" [%s]:\"%s\"", answerNumber, answer.text());
        }
    }

    private int getStudentAnswer(final int questionsCount) {
        return ioService.readIntForRangeWithPrompt(
                1,
                questionsCount,
                "Please enter the number of the specific answer choice:",
                "Try again, remember you have to choose from the options offered"
        );
    }

    private boolean isAnswerValid(final List<Answer> answers, final int studentAnswerNumber) {
        return answers.get(studentAnswerNumber - 1).isCorrect();
    }
}
