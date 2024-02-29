package jln.hobby.discordttsbot.listener;

import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.service.CommandService;
import jln.hobby.discordttsbot.service.VoiceChannelService;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.Objects;

import javax.annotation.Nonnull;

/**
 * テキストを受け取り音声を返すListener
 */
@Component
public class TextToSpeechListener extends ListenerAdapter {

    private final String connectCommand;
    private final String disconnectCommand;
    private final VoiceChannelService voiceChannelService;
    private final CommandService commandService;
    private OffsetDateTime joinTime;

    public TextToSpeechListener(
            TtsBotProperties properties,
            VoiceChannelService voiceChannelService,
            CommandService commandService) {
        this.connectCommand = properties.command.connect;
        this.disconnectCommand = properties.command.disconnect;
        this.voiceChannelService = voiceChannelService;
        this.commandService = commandService;
        this.joinTime = OffsetDateTime.now();
    }

    @Override
    public void onMessageReceived(@Nonnull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (Objects.equals(content, connectCommand)) {
            joinTime = OffsetDateTime.now();
            voiceChannelService.connect(event);
            return;
        } else if (Objects.equals(content, disconnectCommand)) {
            voiceChannelService.disconnect(event);
            return;
        }

        // Skip messages received while being absent
        if (event.getMessage().getTimeCreated().isBefore(joinTime)) {
            return;
        }

        if (commandService.isIgnored(content)) {
            return;
        }

        voiceChannelService.textToSpeech(content, event.getGuild());
    }
}
