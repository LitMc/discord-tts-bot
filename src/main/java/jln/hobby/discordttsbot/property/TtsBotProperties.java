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

    public final Command command;

    public TtsBotProperties(Discord discord, Command command) {
        this.discord = discord;
        this.command = command;
    }

    public Discord getDiscord() {
        return discord;
    }

    public Command getCommand() {
        return command;
    }

    public static class Discord {
        public final String botToken;

        public Discord(String botToken) {
            this.botToken = botToken;
        }

        public String getBotToken() {
            return botToken;
        }
    }

    public static class Command {
        public final String connect;

        public final String disconnect;

        public Command(String connect, String disconnect) {
            this.connect = connect;
            this.disconnect = disconnect;
        }

        public String getConnect() {
            return connect;
        }

        public String getDisconnect() {
            return disconnect;
        }
    }
}
