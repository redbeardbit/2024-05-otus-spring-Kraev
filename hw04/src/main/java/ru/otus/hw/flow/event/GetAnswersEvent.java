package ru.otus.hw.flow.event;

import org.jeasy.states.api.AbstractEvent;

public class GetAnswersEvent extends AbstractEvent {

    public GetAnswersEvent() {
        super("GetAnswersEvent");
    }

    protected GetAnswersEvent(String name) {
        super(name);
    }
}
