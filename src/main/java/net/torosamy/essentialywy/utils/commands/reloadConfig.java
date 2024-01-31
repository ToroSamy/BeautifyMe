package net.torosamy.essentialywy.utils.commands;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.utils.DataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class reloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if (!label.equalsIgnoreCase("essy")) {
            return true;
        }

        if (!(strings.length == 1)) {
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("parameter-error")));
            if (commandSender instanceof Player){
                commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("parameter-error")));
            }
            return true;
        }
        if(strings[0].equals("reload")){
            DataManager.reloadConfig();
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("reload-successfully")));
            return true;
        }else {
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("spell-error")));
            if (commandSender instanceof Player){
                commandSender.sendMessage(EssentialYwY.getLang().get("spell-error"));
            }
        }
        return true;
    }
}
