package org.engineergaming.hinklecraft;

import java.util.Random;

import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import net.kyori.adventure.text.minimessage.MiniMessage;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class InventoryRandomizer implements Listener{
    private static final int changeChance = 5000;
    private final Random random = new Random();

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent event) {
        if(random.nextInt(changeChance)==1){
            Material newMat = Material.values()[random.nextInt(Material.values().length)];
            if (newMat.isItem()) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        int slot = random.nextInt(27)+9;
                        ItemStack replacedItem = event.getPlayer().getInventory().getItem(slot);
                        if(replacedItem != null) {
                            event.getPlayer().sendMessage(MiniMessage
                                .miniMessage()
                                .deserialize("<green><bold>[Hinklecraft] <red>Unlucky!</bold> <red>The slot we needed had your <italic><blue>" + replacedItem.getType().name() + "</italic> <red>in it. Maybe we replaced it with something better..."));
                        } else {
                            event.getPlayer().sendMessage(MiniMessage
                            .miniMessage()
                            .deserialize("<green><bold>[Hinklecraft] <blue>Here!</bold> <blue>Enjoy this free item! Thankfully the slot we needed was open!"));
                        }
                        event.getPlayer().getInventory().setItem(slot, new ItemStack(newMat, random.nextInt(newMat.getMaxStackSize())+1));
                    };
                }.runTaskLater(plugin, 1L);
            }
        }
    }
}
