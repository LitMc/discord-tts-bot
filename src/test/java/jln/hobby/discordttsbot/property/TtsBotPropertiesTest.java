package jln.hobby.discordttsbot.property;

import jln.hobby.discordttsbot.configuration.TestTtsBotConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Bot設定の読み込みテスト
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = TestTtsBotConfiguration.class,
        initializers = ConfigDataApplicationContextInitializer.class
)
@ActiveProfiles(profiles = {"test"})
class TtsBotPropertiesTest {
    private final TtsBotProperties ttsBotProperties;

    @Autowired
    public TtsBotPropertiesTest(TtsBotProperties ttsBotProperties) {
        this.ttsBotProperties = ttsBotProperties;
    }

    @Test
    @DisplayName("application.yamlのtts-bot配下の設定値を読めること")
    void testGetBotToken() {
        assertEquals("dummy bot token", ttsBotProperties.discord.botToken);
        assertEquals("k.con", ttsBotProperties.command.connect);
        assertEquals("k.dc", ttsBotProperties.command.disconnect);
        assertEquals("https://texttospeech.googleapis.com/v1/text:synthesize", ttsBotProperties.google.textToSpeech.apiUrl);
        assertEquals("dummy auth token", ttsBotProperties.google.textToSpeech.token);
    }

}