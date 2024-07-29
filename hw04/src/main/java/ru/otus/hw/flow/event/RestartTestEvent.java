package ru.otus.hw.flow.event;

import org.jeasy.states.api.AbstractEvent;

public class RestartTestEvent extends AbstractEvent {

    public RestartTestEvent() {
        super("RestartTestEvent");
    }

    protected RestartTestEvent(String name) {
        super(name);
    }
}
