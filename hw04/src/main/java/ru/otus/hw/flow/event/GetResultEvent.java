package ru.otus.hw.flow.event;

import org.jeasy.states.api.AbstractEvent;

public class GetResultEvent extends AbstractEvent {

    public GetResultEvent() {
        super("GetResultEvent");
    }

    protected GetResultEvent(String name) {
        super(name);
    }
}
