package org.engineergaming.hinklecraft;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CoalListener implements Listener {
    private final Random random = new Random();
    int totalItems = 0;

    @EventHandler
    public void onCoalBreak(BlockDropItemEvent event) {
        if ((event.getBlockState().getBlock().getType() == Material.COAL_ORE) || (event.getBlockState().getBlock().getType() == Material.DEEPSLATE_COAL_ORE)) {
            boolean coalFound = false;
            for (int i = 0; i < event.getItems().size(); i++) {
                if (event.getItems().get(i).getItemStack().getType() == Material.COAL) {
                    coalFound = true;
                    totalItems += event.getItems().get(i).getItemStack().getAmount();
                }
            }
            if (!coalFound) {return;}
            World world = event.getBlock().getWorld();
            // Spawn between 4 and 6 stacks of extra items
            int extra = 4+random.nextInt(2); 
            new BukkitRunnable() {
                @Override
                public void run() {
                    for (int i = 0; i < extra*totalItems; i++) {
                        world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COAL, 64));
                    }
                    world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COAL, random.nextInt(64)));
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1L);
        }
    }
}