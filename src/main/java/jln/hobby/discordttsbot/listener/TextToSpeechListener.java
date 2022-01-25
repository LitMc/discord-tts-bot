package jln.hobby.discordttsbot.listener;

import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.service.CommandService;
import jln.hobby.discordttsbot.service.VoiceChannelService;
import net.dv8tion.jda.api.entities.Message;
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

    private final String connectCommand;
    private final String disconnectCommand;
    private final VoiceChannelService voiceChannelService;
    private final CommandService commandService;

    public TextToSpeechListener(
            TtsBotProperties properties,
            VoiceChannelService voiceChannelService,
            CommandService commandService
    ) {
        this.connectCommand = properties.command.connect;
        this.disconnectCommand = properties.command.disconnect;
        this.voiceChannelService = voiceChannelService;
        this.commandService = commandService;
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (Objects.equals(content, connectCommand)) {
            voiceChannelService.connect(event);
            return;
        } else if (Objects.equals(content, disconnectCommand)) {
            voiceChannelService.disconnect(event);
            return;
        }

        if (commandService.isIgnored(content)) {
            return;
        }

        voiceChannelService.textToSpeech(content, event.getGuild());
    }
}
