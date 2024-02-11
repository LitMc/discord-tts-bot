package jln.hobby.discordttsbot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * 起動テスト
 */
@SpringBootTest
// 起動に実Tokenが要るのでここだけtestプロファイル未使用
@ActiveProfiles(profiles = {"personal"})
class DiscordTtsBotApplicationTests {

	@Test
	void contextLoads() {
	}

}
