package net.torosamy.essentialywy.welcome;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WelcomeYwYCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if (!label.equalsIgnoreCase("wcy")) {
            return true;
        }

        if (!(strings.length == 1)) {
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("welcome-parameter-error")));
            if (commandSender instanceof Player){
                commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("welcome-parameter-error")));
            }
            return true;
        }
        if(strings[0].equals("reload")){
            WelcomeYwY.loadConfig();
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("welcome-reload-successfully")));
            return true;
        }else {
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("welcome-spell-error")));
            if (commandSender instanceof Player){
                commandSender.sendMessage(EssentialYwY.getLang().get("welcome-spell-error"));
            }
        }
        return true;
    }
}
