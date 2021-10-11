package jln.hobby.discordttsbot.service;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import jln.hobby.discordttsbot.property.TtsBotProperties;
import jln.hobby.discordttsbot.sendhandler.AudioPlayerSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.stereotype.Service;
import org.springframework.util.ConcurrentReferenceHashMap;

import java.util.Objects;

@Service
public class VoiceChannelServiceImpl implements VoiceChannelService {

    private final TtsBotProperties properties;
    private final AudioPlayerManager audioPlayerManager;
    private final ConcurrentReferenceHashMap<String, AudioPlayerSendHandler> audioPlayerSendHandlerMap;

    public VoiceChannelServiceImpl(
            TtsBotProperties properties,
            AudioPlayerManager audioPlayerManager,
            ConcurrentReferenceHashMap<String, AudioPlayerSendHandler> audioPlayerSendHandlerMap
    ) {
        this.properties = properties;
        this.audioPlayerManager = audioPlayerManager;
        this.audioPlayerSendHandlerMap = audioPlayerSendHandlerMap;
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

        // Guild毎に異なるAudioPlayerSendHandlerを使う
        // https://github.com/DV8FromTheWorld/JDA/wiki/4%29-Making-a-Music-Bot#a-working-example
        audioPlayerSendHandlerMap.putIfAbsent(
                guild.getId(),
                new AudioPlayerSendHandler(audioPlayerManager.createPlayer())
        );
        manager.setSendingHandler(audioPlayerSendHandlerMap.get(guild.getId()));
        manager.openAudioConnection(channel);
    }

    @Override
    public void disconnect(GuildMessageReceivedEvent event) {
        event.getGuild().getAudioManager().closeAudioConnection();
    }
}
