package org.engineergaming.hinklecraft;

import java.util.Random;

import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;

public class InventoryRandomizer implements Listener{
    private static final int changeChance = 10;
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
                        event.getPlayer().getInventory().setItem(random.nextInt(27)+9, new ItemStack(newMat, newMat.getMaxStackSize()));
                    };
                }.runTaskLater(plugin, 1L);
            }
        }
    }
}
