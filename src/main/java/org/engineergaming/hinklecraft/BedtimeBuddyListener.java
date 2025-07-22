// The idea with this one is that when a player sleeps it spawns some sort of entity near them
// A destructive and obvious idea would be a creeper
// I just enjoy the idea because it would force people to interact or hide from phantoms
// Turning off weather would also probably be nice if people can't sleep

package org.engineergaming.hinklecraft;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.player;

public class BedtimeBuddyListener implements Listener {
    @EventHandler
    public void onPlayerSleeps(PlayerBedEnterEvent event) {
        World world = event.getLocatation().getWorld();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int i = 0; i < extra; i++) {
                    world.spawnEntity(event.getLocation(), EntityType.CREEPER);
                }
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);
    }
}