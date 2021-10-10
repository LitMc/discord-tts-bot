package jln.hobby.discordttsbot.sendhandler;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;
import java.util.Objects;

/**
 * AudioPlayerのJDA 4向けラッパー
 */
@Component
public class AudioPlayerSendHandler implements AudioSendHandler {
    private final AudioPlayer audioPlayer;
    private AudioFrame lastFrame;

    public AudioPlayerSendHandler(AudioPlayerManager audioPlayerManager) {
        this.audioPlayer = audioPlayerManager.createPlayer();
    }

    @Override
    public boolean canProvide() {
        lastFrame = audioPlayer.provide();
        return Objects.nonNull(lastFrame);
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        return ByteBuffer.wrap(lastFrame.getData());
    }

    @Override
    public boolean isOpus() {
        return false;
    }
}
