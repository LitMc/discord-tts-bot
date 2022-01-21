package jln.hobby.discordttsbot.dao;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import jln.hobby.discordttsbot.property.TtsBotProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GoogleTextToSpeechDaoImpl implements GoogleTextToSpeechDao {
    private final TextToSpeechClient client;
    private final String languageCode;
    private final SsmlVoiceGender ssmlVoiceGender;
    private final String name;
    private final AudioConfig audioConfig;

    public GoogleTextToSpeechDaoImpl(TtsBotProperties properties) throws IOException {
        this.client = TextToSpeechClient.create();
        this.languageCode = properties.google.textToSpeech.languageCode;
        this.ssmlVoiceGender = properties.google.textToSpeech.ssmlGender;
        this.name = properties.google.textToSpeech.name;
        this.audioConfig = AudioConfig.newBuilder()
                .setAudioEncoding(properties.google.textToSpeech.audioEncoding)
                .setSpeakingRate(properties.google.textToSpeech.speakingRate)
                .setSampleRateHertz(48000) // 48000決め打ちなのでプロパティにしない
                .setPitch(properties.google.textToSpeech.pitch)
                .build();
    }

    @Override
    public ByteString getAudioContents(String text) {
        SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();
        VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                .setLanguageCode(languageCode)
                .setSsmlGender(ssmlVoiceGender)
                .setName(name)
                .build();
        SynthesizeSpeechResponse response = client.synthesizeSpeech(input, voice, audioConfig);
        return response.getAudioContent();
    }
}
