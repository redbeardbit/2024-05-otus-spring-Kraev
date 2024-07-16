package ru.otus.hw.flow.event;

import org.jeasy.states.api.AbstractEvent;

public class BeginTestEvent extends AbstractEvent {

    public BeginTestEvent() {
        super("BeginTestEvent");
    }

    protected BeginTestEvent(String name) {
        super(name);
    }

}
