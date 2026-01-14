package net.torosamy.beautifyMe.config;


import net.torosamy.torosamyCore.config.IConfigManage;

import java.util.List;


public class MainConfig implements IConfigManage{
    public Broadcast broadcast = new Broadcast();
    public class Broadcast implements IConfigManage {
        public Boolean enabled;
        public Integer time;
        public List<List<String>> messages;
        public String customPermission;
    }


    public ScoreBoard scoreboard = new ScoreBoard();
    public class ScoreBoard implements IConfigManage {
        public Boolean enabled;
        public Integer time;
        public String customPermission;
        
        public Board board = new Board();
        public class Board implements IConfigManage {
            public String title;
            public List<String> lines;
        }
    }

    public TabList tabList = new TabList();
    public class TabList implements IConfigManage {
        public Boolean headerEnabled;
        public Boolean footerEnabled;
        public Boolean nameListEnabled;
        public Integer time;
        public List<String> header;
        public List<String> footer;
        public String nameList;
        public String customHeaderPermission;
        public String customFooterPermission;
        public String customNameListPermission;
    }


    public BossBar bossbar = new BossBar();
    public class BossBar implements IConfigManage {
        public Boolean enabled;
        public Integer time;

        public Info info = new Info();
        public class Info implements IConfigManage {
            public String style;
            public String color;
            public String text;
        }
    }

    public JoinBroadcast joinBroadcast = new JoinBroadcast();
    public class JoinBroadcast implements IConfigManage {
        public Boolean enabled;
        public String title;
        public String subTitle;
        public String customPermission;
    }

    public JoinMessage joinMessage = new JoinMessage();
    public class JoinMessage implements IConfigManage {
        public Boolean enabled;
        public String message;
        public String customPermission;
    }

    public QuitMessage quitMessage = new QuitMessage();
    public class QuitMessage implements IConfigManage {
        public Boolean enabled;
        public String message;
        public String customPermission;
    }

    public FirstJoinMessage firstJoinMessage = new FirstJoinMessage();
    public class FirstJoinMessage implements IConfigManage {
        public Boolean enabled;
        public String message;
    }
    
    public NameTag nameTag = new NameTag();
    public class NameTag implements IConfigManage {
        public Integer time;
        public Boolean enabled;
        public String prefix;
        public String suffix;
        public String below;
        public String customPermission;
    }
    
    public JoinMotd joinMotd = new JoinMotd();
    public class JoinMotd implements IConfigManage {
        public Boolean enabled;
        public List<String> messages;
        public String customPermission;
    }
    
    public String titleBigPrefix;
    public String titleSmallPrefix;
}
