package jln.hobby.discordttsbot.service;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * 入退室などボイスチャンネル関連を扱うサービス
 */
public interface VoiceChannelService {
    /**
     * ボイスチャンネルに入室する
     */
    void connect(MessageReceivedEvent event);

    /**
     * ボイスチャンネルから退室する
     */
    void disconnect(MessageReceivedEvent event);

    /**
     * テキストを読み上げる
     *
     * @param text  テキスト
     * @param guild Guild（サーバ）
     */
    void textToSpeech(String text, Guild guild);
}
