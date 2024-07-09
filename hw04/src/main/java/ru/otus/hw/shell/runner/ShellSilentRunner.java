package ru.otus.hw.shell.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.service.TestRunnerService;

@ShellComponent
@RequiredArgsConstructor
public class ShellSilentRunner extends AbstractShellComponent {

    private final TestRunnerService testRunnerService;

    @ShellMethod("Silent run otus hw")
    public void run() {
        testRunnerService.run();
    }
}
