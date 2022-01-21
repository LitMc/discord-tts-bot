package jln.hobby.discordttsbot.service;

/**
 * 文字列に含まれるコマンドを判別する
 */
public interface CommandService {

    /**
     * 読み上げないテキストかどうか返す
     * @param text テキスト
     * @return 読み上げないならTrue、読み上げるならFalse
     */
    boolean isIgnored(String text);
}
