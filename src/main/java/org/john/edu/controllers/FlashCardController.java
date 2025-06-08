package org.john.edu.controllers;

import com.zgamelogic.annotations.DiscordController;
import com.zgamelogic.annotations.DiscordMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.john.edu.models.FlashCard;
import org.john.edu.services.FlashCardService;

import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

@DiscordController
@Slf4j
@RequiredArgsConstructor
public class FlashCardController {

    private final FlashCardService flashCardService;

    @DiscordMapping(Id = "flashcards")
    public void flashcardsSlashCommand(SlashCommandInteractionEvent event) {
        var attachment = Objects.requireNonNull(event.getOption("file")).getAsAttachment();

        if (!attachment.isImage()) {
            event.reply("Please upload an image file.").setEphemeral(true).queue();
            return;
        }

        event.deferReply(true).queue(hook -> {
            try {
                var flashCards = flashCardService.generateFlashcards(attachment.getUrl());
                var embeds = new MessageEmbed[flashCards.size()];
                for (int i = 0; i < flashCards.size(); i++)
                    embeds[i] = createFlashcardEmbed(flashCards.get(i));

                hook.sendMessageEmbeds(embeds[0], Arrays.copyOfRange(embeds, 1, embeds.length)).queue();
            } catch (Exception e) {
                hook.sendMessage("An error occurred while processing your request.").queue();
                FlashCardController.log.error("An error occurred while processing the request.", e);
            }
        });
    }

    private static MessageEmbed createFlashcardEmbed(FlashCard flashCard) {
        return new EmbedBuilder()
                .setColor(Color.CYAN)
                .addField(flashCard.question(), "||"+flashCard.answer()+"||", true)
                .build();
    }
}
