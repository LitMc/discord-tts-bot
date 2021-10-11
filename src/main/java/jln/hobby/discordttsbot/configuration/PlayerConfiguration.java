package jln.hobby.discordttsbot.configuration;

import com.sedmelluq.discord.lavaplayer.format.StandardAudioDataFormats;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import jln.hobby.discordttsbot.lavaplayer.AudioPlayerSendHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ConcurrentReferenceHashMap;

/**
 * 音声再生クラス関連のBean定義
 */
@Configuration
public class PlayerConfiguration {
    @Bean
    public AudioPlayerManager audioPlayerManager() {
        AudioPlayerManager audioPlayerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
        audioPlayerManager.getConfiguration().setOutputFormat(StandardAudioDataFormats.DISCORD_OPUS);
        return audioPlayerManager;
    }

    @Bean
    public ConcurrentReferenceHashMap<String, AudioPlayerSendHandler> audioPlayerSendHandlerMap() {
        return new ConcurrentReferenceHashMap<>();
    }
}
