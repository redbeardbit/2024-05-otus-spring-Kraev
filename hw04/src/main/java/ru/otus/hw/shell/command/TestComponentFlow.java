package ru.otus.hw.shell.command;


import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.component.MultiItemSelector;
import org.springframework.shell.component.StringInput;
import org.springframework.shell.component.support.SelectorItem;
import org.springframework.shell.standard.AbstractShellComponent;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ShellComponent
public class TestComponentFlow extends AbstractShellComponent {

    @Bean
    CommandRegistration completeCommandRegistration1() {
        System.out.println("!!!");
        return CommandRegistration.builder()
                .command("m")
                .withTarget()
                .function(ctx -> {
                    String arg = ctx.getOptionValue("arg");
                    ctx.getTerminal().writer().println("hi");
                    return String.format("hi, arg value is '%s'", arg);
                })
                .and()
                .withOption()
                .longNames("arg")
                .and()
                .build();
    }

    @ShellMethod(key = "c", value = "String input", group = "Components")
    public String stringInput(boolean mask) {
        StringInput component = new StringInput(getTerminal(), "Enter value", "myvalue");
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        if (mask) {
            component.setMaskCharacter('*');
        }
        StringInput.StringInputContext context = component.run(StringInput.StringInputContext.empty());
        return "Got value " + context.getResultValue();
    }


    @ShellMethod(key = "multi", value = "Multi selector", group = "Components")
    public String multiSelector(
            @ShellOption(defaultValue = ShellOption.NULL) Boolean longKeys
    ) {
        List<SelectorItem<String>> items = new ArrayList<>();
        items.add(SelectorItem.of("key1", "value1"));
        items.add(SelectorItem.of("key2", "value2", false, true));
        items.add(SelectorItem.of("key3", "value3"));
        if (longKeys != null && longKeys) {
            items.add(SelectorItem.of("key4 long long long long long", "value4", false, true));
            items.add(SelectorItem.of("key5 long long long long long long long long long long", "value5"));
        }
        MultiItemSelector<String, SelectorItem<String>> component = new MultiItemSelector<>(getTerminal(),
                items, "testSimple", null);
        component.setResourceLoader(getResourceLoader());
        component.setTemplateExecutor(getTemplateExecutor());
        MultiItemSelector.MultiItemSelectorContext<String, SelectorItem<String>> context = component
                .run(MultiItemSelector.MultiItemSelectorContext.empty());
        String result = context.getResultItems().stream()
                .map(si -> si.getItem())
                .collect(Collectors.joining(","));
        return "Got value " + result;
    }


}
