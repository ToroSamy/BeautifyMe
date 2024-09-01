package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.commands.AdminCommands
import net.torosamy.beautifyMe.commands.PlayerCommands
import net.torosamy.torosamyCore.TorosamyCore

class CommandUtil {
    companion object {
        fun registerCommand() {
            TorosamyCore.commanderManager.annotationParser.parse(AdminCommands())
            TorosamyCore.commanderManager.annotationParser.parse(PlayerCommands())
        }
    }
}