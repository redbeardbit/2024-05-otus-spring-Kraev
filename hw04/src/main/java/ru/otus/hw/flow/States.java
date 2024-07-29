package ru.otus.hw.flow;

import org.jeasy.states.api.State;
import org.springframework.stereotype.Component;

@Component
public class States {

    public static final State BEGIN_TEST = new State("beginTest");

    public static final State GET_ANSWERS = new State("getAnswers");

    public static final State GET_RESULT = new State("getResult");

    public static final State END_TEST = new State("endTest");

}
