package jln.hobby.discordttsbot.configuration;

import jln.hobby.discordttsbot.listener.TextToSpeechListener;
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

    private final TextToSpeechListener textToSpeechListener;
    private final TtsBotProperties ttsBotProperties;

    public TtsBotConfiguration(TextToSpeechListener textToSpeechListener, TtsBotProperties ttsBotProperties) {
        this.textToSpeechListener = textToSpeechListener;
        this.ttsBotProperties = ttsBotProperties;
    }

    /**
     * JDAクライアントのBean生成
     */
    @Bean
    public JDA jda() throws LoginException, InterruptedException {
        return JDABuilder.createDefault(ttsBotProperties.discord.botToken)
                .addEventListeners(textToSpeechListener)
                .build()
                .awaitReady();
    }

}
