package ru.otus.hw;


import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.shell.command.annotation.CommandScan;
import org.springframework.shell.jline.PromptProvider;


@SpringBootApplication
@CommandScan
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        // TODO: follow up with boot why spring.main.banner-mode=off doesn't work
        // SpringApplication.run(SpringShellSample.class, args);
    }

    @Bean
    public PromptProvider myPromptProvider() {
        return () -> new AttributedString("otus:>", AttributedStyle.DEFAULT.foreground(AttributedStyle.YELLOW));
    }
}