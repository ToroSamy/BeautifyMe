## Dependency
- TorosamyCore
- PlaceholderAPI
## Function
- Custom and switchable broadcast scoreboard tab broadcast
- black and white list
- Quick command to send title
## Usage
1. download [TorosamyCore](https://github.com/ToroSamy/TorosamyCore) and [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) as a dependency plugin
2. put the **dependencies** and this plugin into your plugins folder and start your server
3. Modify the default configuration file generated and restart your server
## Permission
- - **Usage:** /bm reload
  - **Description:** reload this plugin
  - **Permission:** beautifyme.reload
  <br>
- - **Usage:** /bm toggle broadcast player-name
  - **Description:** Switch other player`s broadcast status
  - **Permission:** beautifyme.toggle.broadcast.other
  <br>
- - **Usage:** /bm toggle scoreboard player-name
  - **Description:** Switch other player`s scoreboard status
  - **Permission:** beautifyme.toggle.scoreboard.other
  <br>
- - **Usage:** /bm toggle tab player-name
  - **Description:** Switch other player`s tab status
  - **Permission:** beautifyme.toggle.tab.other
  <br>
- - **Usage:** /bm toggle bossbar player-name
  - **Description:** Switch other player`s bossbar status
  - **Permission:** beautifyme.toggle.bossbar.other
  <br>
- - **Usage:** /bm toggle broadcast
  - **Description:** Switch the player's own broadcast state
  - **Permission:** beautifyme.toggle.broadcast.other
  <br>
- - **Usage:** /bm toggle scoreboard
  - **Description:** Switch the player's own scoreboard state
  - **Permission:** beautifyme.toggle.scoreboard.other
  <br>
- - **Usage:** /bm toggle tab
  - **Description:** Switch the player's own tab state
  - **Permission:** beautifyme.toggle.tab.other
  <br>
- - **Usage:** /bm toggle bossbar
  - **Description:** Switch the player's own bossbar state
  - **Permission:** beautifyme.toggle.bossbar.other
  <br>
- - **Usage:** /bm title main sub
  - **Description:** Send title to all online players
  - **Permission:** beautifyme.title.all
  <br>
- - **Usage:** /bm title main sub player
  - **Description:** Send title to a single players
  - **Permission:** beautifyme.title.single
## Config

### config.yml
```yml
broadcast:
  enabled: true
  # 单位：秒
  time: 300
  default-all-start: true
  messages:
    text1:
      - '&6&m             '
      - 'First line of information'
    text2:
      - 'First line of information'
      - 'Second line of information'
    custom-name:
      - 'First line of information'
      - 'Second line of information'

scoreboard:
  enabled: true
  # 单位：秒
  time: 5
  default-all-start: true
  board:
    title: '&6[ &eTitle &6]'
    lines:
      - '&6&m                         '
      - '&6 ▪ &6&lname: &f:&e%player_name%'
      - '&6 ▪ &6&lother info'
      - '&6&m                         '

tab-list:
  enabled: true
  time: 5
  default-all-start: true
  header:
    - 'First line of information'
    - 'Second line of information'
  footer:
    - 'First line of information'
    - 'Second line of information'
  name-list: '&6prifix &f%player_name% &6suffix'

bossbar:
  enabled: true
  time: 5
  default-all-start: true
  info:
    # SEGMENTED_10 / SEGMENTED_20 / SEGMENTED_6 / SOLID
    style: SOLID
    # BLUE, GREEN, PINK, PURPLE, RED, WHITE, YELLOW
    color: YELLOW 
    text: '&einfo'

join-broadcast:
  enabled: true
  title: '&6&lmain-title'
  sub-title: '&e&lsub-title'
```
### player-toggle.yml
```yml
scoreboard:
  close: []
  open: []
broadcast:
  close: []
  open: []
tab-list:
  close: []
  open: []
bossbar:
  close: []
  open: []
```
### lang.yml
```yml
broadcast-toggle-open: "&b[服务器娘]&a成功 &e开启 &a自动公告喵~"
broadcast-toggle-close: "&b[服务器娘]&a成功 &e关闭 &a自动公告喵~"
broadcast-disabled: "&b[服务器娘]&a服务器没有启用 &e自动公告 &a喵~"
scoreboard-toggle-open: "&b[服务器娘]&a成功 &e开启 &a计分板喵~"
scoreboard-toggle-close: "&b[服务器娘]&a成功 &e关闭 &a计分板喵~"
scoreboard-disabled: "&b[服务器娘]&a服务器没有启用 &e计分板 &a喵~"
tab-list-toggle-open: "&b[服务器娘]&a成功 &e开启 &aTab列表喵~"
tab-list-toggle-close: "&b[服务器娘]&a成功 &e关闭 &aTab列表喵~"
tab-list-disabled: "&b[服务器娘]&a服务器没有启用 &eTab列表 &a喵~"
bossbar-toggle-open: "&b[服务器娘]&a成功 &e开启 &abossbar喵~"
bossbar-toggle-close: "&b[服务器娘]&a成功 &e关闭 &abossbar喵~"
bossbar-disabled: "&b[服务器娘]&a服务器没有启用 &ebossbar &a喵~"
reload-message: "&b[服务器娘]&a插件 &eBeautifyMe &a重载成功喵~"
send-success: "&b[服务器娘]&a发送成功!"
```

## Contact Author

- qq: 1364596766
- website: https://www.torosamy.net

## License

[GPL-3.0 license](./LICENSE)
