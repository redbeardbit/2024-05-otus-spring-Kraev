package ru.otus.hw.flow;

import lombok.Getter;
import org.jeasy.states.api.FiniteStateMachine;
import org.jeasy.states.api.State;
import org.jeasy.states.core.FiniteStateMachineBuilder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

import static ru.otus.hw.flow.States.BEGIN_TEST;
import static ru.otus.hw.flow.States.GET_ANSWERS;
import static ru.otus.hw.flow.States.GET_RESULT;
import static ru.otus.hw.flow.States.END_TEST;
import static ru.otus.hw.flow.Transitions.BEGIN_TEST_TRANSITION;
import static ru.otus.hw.flow.Transitions.GET_ANSWERS_TRANSITION;
import static ru.otus.hw.flow.Transitions.GET_RESULT_TRANSITION;
import static ru.otus.hw.flow.Transitions.RESTART_TEST_TRANSITION;

@Component
@Getter
public class Flow {

    private FiniteStateMachine stateMachine = new FiniteStateMachineBuilder(
            new HashSet<State>(List.of(BEGIN_TEST, GET_ANSWERS, GET_RESULT, END_TEST)),
            BEGIN_TEST
    ).registerTransition(BEGIN_TEST_TRANSITION)
        .registerTransition(GET_ANSWERS_TRANSITION)
        .registerTransition(GET_RESULT_TRANSITION)
        .registerTransition(RESTART_TEST_TRANSITION)
        .build();
}
