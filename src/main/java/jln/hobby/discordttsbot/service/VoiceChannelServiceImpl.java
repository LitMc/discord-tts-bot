package jln.hobby.discordttsbot.service;

import com.google.protobuf.ByteString;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import jln.hobby.discordttsbot.dao.GoogleTextToSpeechDao;
import jln.hobby.discordttsbot.lavaplayer.TextToSpeechSendHandler;
import jln.hobby.discordttsbot.lavaplayer.GuildInstanceManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoiceChannelServiceImpl implements VoiceChannelService {

    private final AudioPlayerManager audioPlayerManager;
    private final GoogleTextToSpeechDao googleTextToSpeechDao;
    private final Map<String, GuildInstanceManager> guildInstanceMap;

    public VoiceChannelServiceImpl(
            AudioPlayerManager audioPlayerManager,
            GoogleTextToSpeechDao googleTextToSpeechDao
    ) {
        this.audioPlayerManager = audioPlayerManager;
        this.googleTextToSpeechDao = googleTextToSpeechDao;
        this.guildInstanceMap = new ConcurrentHashMap<>();
    }

    private synchronized GuildInstanceManager getGuildAudioPlayer(Guild guild) {
        // Guild毎に異なるSendHandlerを使う
        // https://github.com/DV8FromTheWorld/JDA/wiki/4%29-Making-a-Music-Bot#a-working-example
        guildInstanceMap.putIfAbsent(
                guild.getId(),
                new GuildInstanceManager(audioPlayerManager)
        );

        TextToSpeechSendHandler handler = guildInstanceMap.get(guild.getId()).getSendHandler();
        guild.getAudioManager().setSendingHandler(handler);
        return guildInstanceMap.get(guild.getId());
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

        getGuildAudioPlayer(guild);
        manager.openAudioConnection(channel);
    }

    @Override
    public void disconnect(GuildMessageReceivedEvent event) {
        event.getGuild().getAudioManager().closeAudioConnection();
    }

    @Override
    public void textToSpeech(String text, Guild guild) {
        ByteString audioContents = googleTextToSpeechDao.getAudioContents(text);
        guildInstanceMap.get(guild.getId()).getSendHandler().enqueue(audioContents.toByteArray());
    }
}
