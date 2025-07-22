// The idea with this one is that when someone tries to mine diamonds it just kills them
// My thought is with explosions but it can proabably be updated to whatever

package org.engineergaming.hinklecraft;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Explosive;
import org.bukkit.block;
import org.bukkit.event.block;

import java.util.Random;

public class DiamondBreakListener implements Listener {
    private final Random random = new Random();

    @EventHandler
    public void onDiamondBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() == Material.DIAMOND_ORE) || (event.getBlock().getType() = Material.DEEPSLATE_DIAMOND_ORE) {
            World world = event.getLocation().getWorld();
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(random.nextInt(10)<=1){
                        world.createExplosion(event.getBlock().getLocatation(), 16F)
                    }
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1L);
        }
    }
}