package jln.hobby.discordttsbot.listener;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

/**
 * テキストを受け取りテキストを返すListener
 */
@Component
public class TextToTextListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot()) {
            return;
        }
        Message message = event.getMessage();
        String content = message.getContentRaw();
        if (content.equals("!ping")) {
            MessageChannel messageChannel = event.getChannel();
            messageChannel.sendMessage("Pong!").queue();
        }
    }
}
