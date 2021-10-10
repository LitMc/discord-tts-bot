package jln.hobby.discordttsbot.listener;

import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.service.VoiceChannelService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * テキストを受け取り音声を返すListener
 */
@Component
public class TextToSpeechListener extends ListenerAdapter {

    private final TtsBotProperties properties;
    private final VoiceChannelService service;

    public TextToSpeechListener(TtsBotProperties properties, VoiceChannelService service) {
        this.properties = properties;
        this.service = service;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (event.getAuthor().isBot()) {
            return;
        }

        if (Objects.equals(content, properties.command.connect)) {
            service.connect(event);
        } else if (Objects.equals(content, properties.command.disconnect)) {
            service.disconnect(event);
        }
    }
}
