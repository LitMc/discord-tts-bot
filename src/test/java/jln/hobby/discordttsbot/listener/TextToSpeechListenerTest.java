package jln.hobby.discordttsbot.listener;

import jln.hobby.discordttsbot.configuration.TestTtsBotConfiguration;
import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.service.CommandService;
import jln.hobby.discordttsbot.service.VoiceChannelService;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                TestTtsBotConfiguration.class
        },
        initializers = ConfigDataApplicationContextInitializer.class
)
@MockBean(classes = {
        VoiceChannelService.class,
        GuildMessageReceivedEvent.class,
        CommandService.class
})
class TextToSpeechListenerTest {
    private final TtsBotProperties properties;
    private final VoiceChannelService target;
    private final GuildMessageReceivedEvent eventMock;
    private final CommandService commandServiceMock;

    @Autowired
    public TextToSpeechListenerTest(
            TtsBotProperties properties,
            VoiceChannelService target,
            GuildMessageReceivedEvent eventMock,
            CommandService commandServiceMock
    ) {
        this.properties = properties;
        this.target = target;
        this.eventMock = eventMock;
        this.commandServiceMock = commandServiceMock;
    }

    // FIXME: メソッドチェーンのモック化がめんどうでテストできない
    @Test
    @DisplayName("Botによるメッセージは無視すること")
    void testBotMessage() {
    }
}