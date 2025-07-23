// The idea with this one is that when a player sleeps it spawns some sort of entity near them
// A destructive and obvious idea would be a creeper
// I just enjoy the idea because it would force people to interact or hide from phantoms
// Turning off weather would also probably be nice if people can't sleep

package org.engineergaming.hinklecraft;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class BedtimeBuddyListener implements Listener {
    @EventHandler
    public void onPlayerSleeps(PlayerBedEnterEvent event) {
        World world = event.getBed().getWorld();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1; i++) {
                    world.spawnEntity(event.getPlayer().getLocation(), EntityType.CREEPER);
                }
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);
    }
}