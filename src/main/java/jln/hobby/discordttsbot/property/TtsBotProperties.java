package jln.hobby.discordttsbot.property;

import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
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

    public final Google google;

    public TtsBotProperties(Discord discord, Command command, Google google) {
        this.discord = discord;
        this.command = command;
        this.google = google;
    }

    public static class Discord {
        public final String botToken;

        public Discord(String botToken) {
            this.botToken = botToken;
        }
    }

    public static class Command {
        public final String connect;

        public final String disconnect;

        public Command(String connect, String disconnect) {
            this.connect = connect;
            this.disconnect = disconnect;
        }
    }

    public static class Google {

        public final TextToSpeech textToSpeech;

        public Google(TextToSpeech textToSpeech) {
            this.textToSpeech = textToSpeech;
        }

        public static class TextToSpeech {
            public final String apiUrl;
            public final String token;
            public final String languageCode;
            public final String name;
            public final SsmlVoiceGender ssmlGender;
            public final AudioEncoding audioEncoding;

            public TextToSpeech(
                    String apiUrl,
                    String token,
                    String languageCode,
                    String name,
                    SsmlVoiceGender ssmlGender,
                    AudioEncoding audioEncoding
            ) {
                this.apiUrl = apiUrl;
                this.token = token;
                this.languageCode = languageCode;
                this.name = name;
                this.ssmlGender = ssmlGender;
                this.audioEncoding = audioEncoding;
            }
        }
    }
}
