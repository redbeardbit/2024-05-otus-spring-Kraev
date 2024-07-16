package ru.otus.hw.flow;

import org.jeasy.states.api.Transition;
import org.jeasy.states.core.TransitionBuilder;
import org.springframework.stereotype.Component;
import ru.otus.hw.flow.event.BeginTestEvent;
import ru.otus.hw.flow.event.GetAnswersEvent;
import ru.otus.hw.flow.event.GetResultEvent;
import ru.otus.hw.flow.event.RestartTestEvent;

import static ru.otus.hw.flow.States.BEGIN_TEST;
import static ru.otus.hw.flow.States.GET_ANSWERS;
import static ru.otus.hw.flow.States.GET_RESULT;
import static ru.otus.hw.flow.States.END_TEST;

@Component
public class Transitions {

    public static final Transition BEGIN_TEST_TRANSITION = new TransitionBuilder()
            .name("BEGIN_TEST_TRANSITION")
            .sourceState(BEGIN_TEST)
            .eventType(BeginTestEvent.class)
            .targetState(GET_ANSWERS)
            .build();

    public static final Transition GET_ANSWERS_TRANSITION = new TransitionBuilder()
            .name("GET_ANSWERS_TRANSITION")
            .sourceState(GET_ANSWERS)
            .eventType(GetAnswersEvent.class)
            .targetState(GET_RESULT)
            .build();

    public static final Transition GET_RESULT_TRANSITION = new TransitionBuilder()
            .name("GET_RESULT_TRANSITION")
            .sourceState(GET_RESULT)
            .eventType(GetResultEvent.class)
            .targetState(END_TEST)
            .build();

    public static final Transition RESTART_TEST_TRANSITION = new TransitionBuilder()
            .name("RESTART_TEST_TRANSITION")
            .sourceState(END_TEST)
            .eventType(RestartTestEvent.class)
            .targetState(BEGIN_TEST)
            .build();

}
