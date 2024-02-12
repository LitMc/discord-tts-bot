package jln.hobby.discordttsbot.dao;

import com.google.protobuf.ByteString;
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
                GoogleTextToSpeechDaoImpl.class
        },
        initializers = ConfigDataApplicationContextInitializer.class
)
class GoogleTextToSpeechDaoImplTest {
    private final GoogleTextToSpeechDao target;

    @Autowired
    public GoogleTextToSpeechDaoImplTest(GoogleTextToSpeechDao target) {
        this.target = target;
    }

    @Test
    @DisplayName("テキストを読み上げた音声が取得できること")
    void testGetAudioContents() {
        String text = "hello";
        ByteString actual = target.getAudioContents(text);
        assertNotNull(actual);
    }
}