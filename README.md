[![deploy](https://github.com/LitMc/discord-tts-bot/actions/workflows/deploy.yaml/badge.svg)](https://github.com/LitMc/discord-tts-bot/actions/workflows/deploy.yaml)

# discord-tts-bot
テキストを読み上げるDiscord用Bot

## 開発環境構築
### レポジトリをクローン
```shell script
$ git clone https://github.com/LitMc/discord-tts-bot.git
$ cd discord-tts-bot
```

### Discord側でBotを作成
[JDAのGetting Started](https://github.com/DV8FromTheWorld/JDA/wiki/3%29-Getting-Started) に従いDiscord Developer PortalでBotを作成する。  
作成後BotタブからTokenをコピーしておく。

### ローカル用設定ファイルを作成
`src/main/resources/`配下に`application-personal.yaml`を作成してBotのTokenを貼り付ける。  
```yaml
# application-personal.yaml
tts-bot:
  discord:
    bot-token: <Discord BotのTokenをここに記載>
```

### テスト実行
実行環境が整っているかテスト実行で確かめる。
```shell script
# プロジェクトのルートで作業
$ pwd
/path/to/discord-tts-bot
$ ls
HELP.md              README.md            mvnw*                pom.xml              target/
LICENSE              discord-tts-bot.iml  mvnw.cmd             src/

# テスト実行
$ ./mvnw clean test
[INFO] Scanning for projects...
[INFO]

...（中略）...

[INFO] Results:
[INFO]
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  11.947 s
[INFO] Finished at: 2021-10-09T05:52:27+09:00
[INFO] ------------------------------------------------------------------------
```
BUILD SUCCESSであればよい。失敗してしまったら`src/main/application-personal.yaml`にもう一度Tokenを貼り付けて`./mvnw clean test`してみる。