package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.commands.AdminCommands
import net.torosamy.beautifyMe.commands.PlayerCommands
import net.torosamy.torosamyCore.commands.CommandManager

object CommandUtil {
    private val commanderManager: CommandManager = CommandManager(BeautifyMe.plugin)

    public val ADMIN_COMMANDS: AdminCommands = AdminCommands();
    public val PLAYER_COMMANDS: PlayerCommands = PlayerCommands();

    fun registerCommand() {
        commanderManager.annotationParser.parse(ADMIN_COMMANDS)
        commanderManager.annotationParser.parse(PLAYER_COMMANDS)
    }
}