package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.commands.AdminCommands
import net.torosamy.beautifyMe.commands.PlayerCommands
import net.torosamy.torosamyCore.TorosamyCore

class CommandUtil {
    companion object {
        private var torosamyCorePlugin: TorosamyCore = BeautifyMe.plugin.server.pluginManager.getPlugin("TorosamyCore") as TorosamyCore
        fun registerCommand() {
            torosamyCorePlugin.getCommandManager().annotationParser.parse(AdminCommands())
            torosamyCorePlugin.getCommandManager().annotationParser.parse(PlayerCommands())
        }
    }
}