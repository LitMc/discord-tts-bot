package jln.hobby.discordttsbot.service;

import jln.hobby.discordttsbot.property.TtsBotProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CommandServiceImpl implements CommandService {

    private final List<Pattern> ignorePatternList;

    public CommandServiceImpl(TtsBotProperties properties) {
        ignorePatternList = new ArrayList<>();
        for (String regex : properties.ignorePatterns) {
            ignorePatternList.add(Pattern.compile(regex));
        }
    }

    @Override
    public boolean isIgnored(String text) {
        for (Pattern pattern : ignorePatternList) {
            if (pattern.matcher(text).matches()) {
                return true;
            }
        }
        return false;
    }
}
