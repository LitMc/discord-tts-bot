package jln.hobby.discordttsbot.lavaplayer;

import net.dv8tion.jda.api.audio.*;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * テキスト読み上げ音声の送信処理を担うハンドラ
 */
public class TextToSpeechSendHandler implements AudioSendHandler, AudioReceiveHandler {

    // 16bit, 48000Hz, 2チャンネル 20ms分のバイト数
    public static final int PACK_SIZE_20MS = 3840;
    // wavは44byte目までがヘッダ
    public static final int WAVE_HEADER_SIZE = 44;

    private final Queue<byte[]> queue = new ConcurrentLinkedQueue<>();

    /**
     * ヘッダつきの音声データを再生する
     */
    public void enqueue(byte[] data) {
        byte[] convertedData = convert(data);
        // 20ms毎に分割
        for (int i = 0; i < convertedData.length; i += PACK_SIZE_20MS) {
            queue.add(Arrays.copyOfRange(convertedData, i, i + PACK_SIZE_20MS));
        }
    }

    @Override
    public boolean canProvide() {
        return !queue.isEmpty();
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        byte[] data = queue.poll();
        return ByteBuffer.wrap(data);
    }

    private byte[] convert(byte[] data) {
        // opusエンコードへ渡すのにwavのヘッダは不要
        byte[] byteArray = Arrays.copyOfRange(data, WAVE_HEADER_SIZE, data.length);

        // 1サンプル16bit(2bytes)なので2つ組にする
        short[] shortMono = new short[byteArray.length / 2];
        ByteBuffer
                .wrap(byteArray)
                .order(ByteOrder.LITTLE_ENDIAN)
                .asShortBuffer()
                .get(shortMono);

        // ビッグエンディアンへ変換しつつステレオ化
        ByteBuffer convertedByteBuffer = ByteBuffer.allocate(shortMono.length * 2 * 2);
        for (int i = 0; i + 1 < shortMono.length; i += 2) {
            convertedByteBuffer.putShort(shortMono[i + 1]);
            convertedByteBuffer.putShort(shortMono[i + 1]);
            convertedByteBuffer.putShort(shortMono[i]);
            convertedByteBuffer.putShort(shortMono[i]);
        }
        return convertedByteBuffer.array();
    }

    @Override
    public boolean isOpus() {
        return false;
    }
}
