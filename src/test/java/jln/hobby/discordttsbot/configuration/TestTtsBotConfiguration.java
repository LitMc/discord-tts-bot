package jln.hobby.discordttsbot.configuration;

import jln.hobby.discordttsbot.property.TtsBotProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * テスト用Configuration
 */
@org.springframework.boot.test.context.TestConfiguration
@EnableConfigurationProperties(TtsBotProperties.class)
public class TestTtsBotConfiguration {
}
