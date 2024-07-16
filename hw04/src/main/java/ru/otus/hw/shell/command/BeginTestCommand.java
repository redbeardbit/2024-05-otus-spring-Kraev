package ru.otus.hw.shell.command;

import lombok.RequiredArgsConstructor;
import org.jeasy.states.api.FiniteStateMachineException;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.hw.flow.Flow;
import ru.otus.hw.flow.event.BeginTestEvent;
import ru.otus.hw.flow.event.RestartTestEvent;
import ru.otus.hw.service.StudentService;
import ru.otus.hw.service.TestContextService;

import static ru.otus.hw.flow.States.BEGIN_TEST;
import static ru.otus.hw.flow.States.END_TEST;

@ShellComponent
@RequiredArgsConstructor
public class BeginTestCommand {

    private final Flow flow;

    private final StudentService studentService;

    private final TestContextService testContextService;

    @ShellMethod(value = "Start test", key = {"s", "start test"}, group = "Test Flow Commands")
    @ShellMethodAvailability("isAvailable")
    public void go() throws FiniteStateMachineException {

        checkNeedRestartTest();

        testContextService.getTestContext().setStudent(studentService.determineCurrentStudent());

        flow.getStateMachine().fire(new BeginTestEvent());

    }

    private Availability isAvailable() {
        var currentState = flow.getStateMachine().getCurrentState().getName();
        return (currentState == BEGIN_TEST.getName() || currentState == END_TEST.getName()) ?
                Availability.available() : Availability.unavailable("you've already started testing");
    }

    private void checkNeedRestartTest() throws FiniteStateMachineException {
        if (flow.getStateMachine().getCurrentState().getName() == END_TEST.getName()) {
            flow.getStateMachine().fire(new RestartTestEvent());
        }
    }
}
