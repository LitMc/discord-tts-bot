package jln.hobby.discordttsbot.listener;

import jln.hobby.discordttsbot.configuration.TestTtsBotConfiguration;
import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.sendhandler.AudioPlayerSendHandler;
import jln.hobby.discordttsbot.service.VoiceChannelService;
import jln.hobby.discordttsbot.service.VoiceChannelServiceImpl;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                TestTtsBotConfiguration.class
        },
        initializers = ConfigDataApplicationContextInitializer.class
)
@MockBean(classes = {
        VoiceChannelService.class,
        GuildMessageReceivedEvent.class
})
class TextToSpeechListenerTest {
    private final TtsBotProperties properties;
    private final VoiceChannelService target;
    private final GuildMessageReceivedEvent eventMock;

    @Autowired
    public TextToSpeechListenerTest(
            TtsBotProperties properties,
            VoiceChannelService target,
            GuildMessageReceivedEvent eventMock
    ) {
        this.properties = properties;
        this.target = target;
        this.eventMock = eventMock;
    }

    // FIXME: メソッドチェーンのモック化がめんどうでテストできない
    @Test
    @DisplayName("Botによるメッセージは無視すること")
    void testBotMessage() {
    }
}