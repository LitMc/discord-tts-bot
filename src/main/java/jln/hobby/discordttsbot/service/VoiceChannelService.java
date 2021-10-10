package jln.hobby.discordttsbot.service;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/**
 * 入退室などボイスチャンネル関連を扱うサービス
 */
public interface VoiceChannelService {
    /**
     * ボイスチャンネルに入室する
     */
    void connect(GuildMessageReceivedEvent event);

    /**
     * ボイスチャンネルから退室する
     */
    void disconnect(GuildMessageReceivedEvent event);
}
