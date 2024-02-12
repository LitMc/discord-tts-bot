package jln.hobby.discordttsbot.dao;

import com.google.protobuf.ByteString;

/**
 * Google Text-to-Speech APIを叩くDAO
 */
public interface GoogleTextToSpeechDao {

    /**
     * テキストを読み上げた音声を取得する
     *
     * @param text 読み上げるテキスト
     * @return 音声
     */
    ByteString getAudioContents(String text);
}
