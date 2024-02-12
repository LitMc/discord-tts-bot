package jln.hobby.discordttsbot.lavaplayer;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

/**
 * Guild毎に持つインスタンスをまとめて持つクラス
 */
public class GuildInstanceManager {
    public final AudioPlayer player;
    private final TextToSpeechSendHandler textToSpeechSendHandler;

    public GuildInstanceManager(AudioPlayerManager manager) {
        this.player = manager.createPlayer();
        this.textToSpeechSendHandler = new TextToSpeechSendHandler();
    }

    public TextToSpeechSendHandler getSendHandler() {
        return this.textToSpeechSendHandler;
    }
}
