package net.torosamy.essentialywy.utils.commands;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.utils.DataManager;
import net.torosamy.essentialywy.utils.PluginManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class reloadConfig implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if (!label.equalsIgnoreCase("essy")) {
            return true;
        }
        if("reload".equalsIgnoreCase(strings[0])){
            DataManager.reloadConfig();
            PluginManager.reloadPlugin();
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("reload-successfully")));
            return true;
        }
        EssentialYwY.getLangHelp().get("total-help").forEach(message->commandSender.sendMessage(MessageUtils.text(message)));
        return true;
    }
}
