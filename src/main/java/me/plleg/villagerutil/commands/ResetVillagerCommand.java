package me.plleg.villagerutil.commands;

import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import me.plleg.villagerutil.VillagerUtil;
import me.plleg.villagerutil.items.wands.TradeWand;

public class ResetVillagerCommand implements CommandExecutor {

    // Pattern used to check if a string is a number
    Pattern numPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    // Constructor which initializes the command
    public ResetVillagerCommand(VillagerUtil p) {
        p.getCommand("resetvillager").setExecutor(this);
    }

    // Triggers when the command is run
    // Uses command number to check if command was run
    // using a Villager Trade Wand
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        // Check if the sender is a player
        if (sender instanceof Player) {
            Player p = (Player) sender;
            int commandNumber;

            // Try to parse command number, if it's not a number set it to -1
            if (numPattern.matcher(args[0]).matches()) {
                commandNumber = Integer.parseInt(args[0]);
            } else {
                commandNumber = -1;
            }

            // Check if the command number if correct
            if (commandNumber == VillagerUtil.getCommandNumber()) {
                // Reset villager's trades
                TradeWand.resetLockedTrades(p);
            } else {
                p.sendMessage(ChatColors.color("&cInvalid command!"));
            }
        } else {
            sender.sendMessage(ChatColors.color("&cOnly players are able to execute this command!"));
        }

        return true;
    }

}