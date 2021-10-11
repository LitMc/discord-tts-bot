package jln.hobby.discordttsbot.service;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import jln.hobby.discordttsbot.configuration.TestTtsBotConfiguration;
import jln.hobby.discordttsbot.dao.GoogleTextToSpeechDao;
import jln.hobby.discordttsbot.property.TtsBotProperties;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ConcurrentReferenceHashMap;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                TestTtsBotConfiguration.class,
                VoiceChannelServiceImpl.class
        },
        initializers = ConfigDataApplicationContextInitializer.class
)
@MockBean(classes = {
        GuildMessageReceivedEvent.class,
        GoogleTextToSpeechDao.class,
        AudioPlayerManager.class
})
class VoiceChannelServiceImplTest {
    private final VoiceChannelService target;
    private final GoogleTextToSpeechDao daoMock;
    private final TtsBotProperties properties;
    private final GuildMessageReceivedEvent event;

    @Autowired
    public VoiceChannelServiceImplTest(
            VoiceChannelService target,
            GoogleTextToSpeechDao daoMock,
            TtsBotProperties properties,
            GuildMessageReceivedEvent event
    ) {
        this.target = target;
        this.daoMock = daoMock;
        this.properties = properties;
        this.event = event;
    }

    // FIXME: メソッドチェーンの処理をモック化するのがしんどくテストできない
    @Test
    @DisplayName("接続コマンドを出した人のいるチャンネルに接続すること")
    void testConnect() {
    }

    @Test
    @DisplayName("切断コマンドで切断すること")
    void testDisconnect() {
    }

    @Test
    @DisplayName("ボイスチャンネルにいないユーザの接続要求には接続してからリトライするようテキストで返すこと")
    void testNoUserInChannel() {
    }

    @Test
    @DisplayName("テキスト読み上げリクエストが行えること")
    void testSpeech() {
    }
}