package org.john.edu.config;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfig {

    @Bean
    SlashCommandData flashCardSlashCommandData() {
        return Commands.slash("flashcards", "Flashcard creation")
                .addOptions(
                        new OptionData(OptionType.ATTACHMENT, "file", "A file containing an image of your notes", true));
    }
}
