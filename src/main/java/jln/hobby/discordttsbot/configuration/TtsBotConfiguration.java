package jln.hobby.discordttsbot.configuration;

import jln.hobby.discordttsbot.listener.TextToTextListener;
import jln.hobby.discordttsbot.property.TtsBotProperties;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.security.auth.login.LoginException;

/**
 * Bot用Configuration
 */
@Configuration
@EnableConfigurationProperties(value = {TtsBotProperties.class})
public class TtsBotConfiguration {

    private final TextToTextListener textToTextListener;
    private final TtsBotProperties ttsBotProperties;

    public TtsBotConfiguration(TextToTextListener textToTextListener, TtsBotProperties ttsBotProperties) {
        this.textToTextListener = textToTextListener;
        this.ttsBotProperties = ttsBotProperties;
    }

    /**
     * JDAクライアントのBean生成
     */
    @Bean
    public JDA jda() throws LoginException, InterruptedException {
        return JDABuilder.createDefault(ttsBotProperties.discord.botToken)
                .addEventListeners(textToTextListener)
                .build()
                .awaitReady();
    }
}
