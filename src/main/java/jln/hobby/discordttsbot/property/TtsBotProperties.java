package jln.hobby.discordttsbot.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

/**
 * Bot固有の設定項目
 */
@ConfigurationProperties(prefix = "tts-bot")
@ConstructorBinding
public class TtsBotProperties {

    public final Discord discord;

    public TtsBotProperties(Discord discord) {
        this.discord = discord;
    }

    public static class Discord {
        public final String botToken;

        public Discord(String botToken) {
            this.botToken = botToken;
        }
    }
}
