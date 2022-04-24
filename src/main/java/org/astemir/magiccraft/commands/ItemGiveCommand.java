package org.astemir.magiccraft.commands;

import org.astemir.magiccraft.item.Item;
import org.astemir.magiccraft.item.Items;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ItemGiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (args.length > 0) {
                String id = args[0];
                Item item = Items.getItem(id);
                if (item != null){
                    ((Player)sender).getInventory().addItem(item.toItemStack());
                }
            }
            return true;
        }
        return false;
    }
}
