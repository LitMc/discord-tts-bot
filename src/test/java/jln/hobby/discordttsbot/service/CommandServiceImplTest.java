package jln.hobby.discordttsbot.service;

import jln.hobby.discordttsbot.configuration.TestTtsBotConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                TestTtsBotConfiguration.class,
                CommandServiceImpl.class
        },
        initializers = ConfigDataApplicationContextInitializer.class
)
class CommandServiceImplTest {
    private final CommandService target;

    @Autowired
    public CommandServiceImplTest(CommandService target) {
        this.target = target;
    }

    @Test
    @DisplayName("URLは読み上げないこと")
    void testIsIgnored() {
        assertTrue(target.isIgnored("http://honyarara.com"));
        assertTrue(target.isIgnored("https://honyarara.com"));
        assertTrue(target.isIgnored("URLはhttp://honyarara.com"));
        assertTrue(target.isIgnored("http://honyarara.comがURL"));
        assertTrue(target.isIgnored("https://honyarara.com"));
        assertTrue(target.isIgnored("https://honyarara.com"));
        assertTrue(target.isIgnored("URLはhttps://honyarara.com"));
        assertTrue(target.isIgnored("https://honyarara.comがURL"));
        assertFalse(target.isIgnored("これは読んでください"));
    }
}