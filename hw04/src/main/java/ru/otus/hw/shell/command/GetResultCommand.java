package ru.otus.hw.shell.command;

import lombok.RequiredArgsConstructor;
import org.jeasy.states.api.FiniteStateMachineException;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.hw.flow.Flow;
import ru.otus.hw.flow.event.GetResultEvent;
import ru.otus.hw.service.ResultService;
import ru.otus.hw.service.TestContextService;

import static ru.otus.hw.flow.States.GET_RESULT;

@ShellComponent
@RequiredArgsConstructor
public class GetResultCommand {

    private final Flow flow;

    private final ResultService resultService;

    private final TestContextService testContextService;

    @ShellMethod(value = "show test results", key = {"sr", "show result"}, group = "Test Flow Commands")
    @ShellMethodAvailability("isAvailable")
    public void go() throws FiniteStateMachineException {

        resultService.showResult(testContextService.getTestContext().getTestResult());

        flow.getStateMachine().fire(new GetResultEvent());
    }

    public Availability isAvailable() {
        return (flow.getStateMachine().getCurrentState().getName() != GET_RESULT.getName()) ?
                Availability.unavailable("it's not the time for this step") : Availability.available();
    }
}
