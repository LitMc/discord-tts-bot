package jln.hobby.discordttsbot.service;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.sendhandler.AudioPlayerSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class VoiceChannelServiceImpl implements VoiceChannelService {

    private final TtsBotProperties properties;
    private final AudioPlayerSendHandler handler;

    public VoiceChannelServiceImpl(TtsBotProperties properties, AudioPlayerSendHandler handler) {
        this.properties = properties;
        this.handler = handler;
    }

    @Override
    public void connect(GuildMessageReceivedEvent event) {
        Guild guild = event.getGuild();
        GuildVoiceState state = event.getMember().getVoiceState();
        if (!state.inVoiceChannel()) {
            event.getChannel().sendMessage("ボイスチャンネルに入ってから呼んでください").queue();
            return;
        }

        VoiceChannel channel = state.getChannel();
        AudioManager manager = guild.getAudioManager();
        manager.setSendingHandler(handler);
        manager.openAudioConnection(channel);
    }

    @Override
    public void disconnect(GuildMessageReceivedEvent event) {
        event.getGuild().getAudioManager().closeAudioConnection();
    }
}
