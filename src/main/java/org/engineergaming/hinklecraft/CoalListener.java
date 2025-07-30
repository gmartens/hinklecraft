package org.engineergaming.hinklecraft;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CoalListener implements Listener {
    private final Random random = new Random();

    @EventHandler
    public void onCoalBreak(BlockBreakEvent event) {
        if (event.isDropItems() && ((event.getBlock().getType() == Material.COAL_ORE) || (event.getBlock().getType() == Material.DEEPSLATE_COAL_ORE))) {
            World world = event.getBlock().getWorld();
            // Spawn between 4 and 6 stacks of extra items
            int extra = 258+random.nextInt(129); 
            new BukkitRunnable() {
                @Override
                public void run() {
                    world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COAL, extra));
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1L);
        }
    }
}