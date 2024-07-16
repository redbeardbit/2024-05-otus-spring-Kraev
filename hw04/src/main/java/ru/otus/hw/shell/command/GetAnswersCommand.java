package ru.otus.hw.shell.command;

import lombok.RequiredArgsConstructor;
import org.jeasy.states.api.FiniteStateMachineException;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.hw.flow.Flow;
import ru.otus.hw.flow.event.GetAnswersEvent;
import ru.otus.hw.service.TestContextService;
import ru.otus.hw.service.TestService;

import static ru.otus.hw.flow.States.GET_ANSWERS;

@ShellComponent
@RequiredArgsConstructor
public class GetAnswersCommand {

    private final Flow flow;

    private final TestService testService;

    private final TestContextService testContextService;

    @ShellMethod(value = "Answer questions", key = {"a", "enter answers"}, group = "Test Flow Commands")
    @ShellMethodAvailability("isAvailable")
    public void go() throws FiniteStateMachineException {

        var testResult = testService.executeTestFor(testContextService.getTestContext().getStudent());

        testContextService.getTestContext().setTestResult(testResult);

        flow.getStateMachine().fire(new GetAnswersEvent());
    }

    public Availability isAvailable() {
        return (flow.getStateMachine().getCurrentState().getName() != GET_ANSWERS.getName()) ?
                Availability.unavailable("it's not the time for this step") : Availability.available();
    }

}
