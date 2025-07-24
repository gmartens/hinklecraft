package org.engineergaming.hinklecraft;

import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemBreakListener implements Listener {

    @EventHandler
    public void onItemBreak(PlayerItemBreakEvent event) {
        World world = event.getPlayer().getWorld();

        new BukkitRunnable() {
            @Override
            public void run() {
                world.createExplosion(event.getPlayer(), 8f, false);
            }
        }.runTaskLater(Main.getPlugin(Main.class), 0);

    }
    
}
