package net.torosamy.beautifyMe.config;


import net.torosamy.torosamyCore.config.TorosamyConfig;
import java.util.List;


public class MainConfig extends TorosamyConfig{
    public Broadcast broadcast = new Broadcast();
    public class Broadcast extends TorosamyConfig {
        public Boolean enabled;
        public Integer time;
        public Boolean rememberToggleChoice;
        public List<List<String>> messages;
    }


    public ScoreBoard scoreboard = new ScoreBoard();
    public class ScoreBoard extends TorosamyConfig {
        public Boolean enabled;
        public Integer time;
        public Boolean rememberToggleChoice;

        public Board board = new Board();
        public class Board extends TorosamyConfig {
            public String title;
            public List<String> lines;
        }
    }

    public TabList tabList = new TabList();
    public class TabList extends TorosamyConfig {
        public Boolean enabled;
        public Integer time;
        public Boolean rememberToggleChoice;
        public List<String> header;
        public List<String> footer;
        public String nameList;
    }


    public BossBar bossbar = new BossBar();
    public class BossBar extends TorosamyConfig {
        public Boolean enabled;
        public Integer time;
        public Boolean rememberToggleChoice;

        public Info info = new Info();
        public class Info extends TorosamyConfig {
            public String style;
            public String color;
            public String text;
        }
    }

    public JoinBroadcast joinBroadcast = new JoinBroadcast();
    public class JoinBroadcast extends TorosamyConfig {
        public Boolean enabled;
        public String title;
        public String subTitle;
    }
}
