package jln.hobby.discordttsbot.listener;

import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.service.VoiceChannelService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.util.Objects;

import javax.annotation.Nonnull;

/**
 * テキストを受け取り音声を返すListener
 */
@Component
public class TextToSpeechListener extends ListenerAdapter {

    private final String connectCommand;
    private final String disconnectCommand;
    private final VoiceChannelService service;

    public TextToSpeechListener(TtsBotProperties properties, VoiceChannelService service) {
        this.connectCommand = properties.command.connect;
        this.disconnectCommand = properties.command.disconnect;
        this.service = service;
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (Objects.equals(content, connectCommand)) {
            service.connect(event);
            return;
        } else if (Objects.equals(content, disconnectCommand)) {
            service.disconnect(event);
            return;
        }

        service.textToSpeech(content, event.getGuild());
    }
}
