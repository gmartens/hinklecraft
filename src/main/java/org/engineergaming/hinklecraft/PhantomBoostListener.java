package org.engineergaming.hinklecraft;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Phantom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class PhantomBoostListener implements Listener {
    private final Random random = new Random();

    @EventHandler
    public void onPhantomSpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() == EntityType.PHANTOM && (event.getSpawnReason() == SpawnReason.NATURAL || event.getSpawnReason() == SpawnReason.SPAWNER_EGG)) {
            World world = event.getLocation().getWorld();
            if (isNight(world)) {
                // Spawn 2–5 extra phantoms at the same location
                int extra = 2 + random.nextInt(4);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < extra; i++) {
                            world.spawnEntity(event.getLocation(), EntityType.PHANTOM);
                        }
                    }
                }.runTaskLater(Main.getPlugin(Main.class), 1L);
            }
        }
    }

    private boolean isNight(World world) {
        long time = world.getTime();
        return time >= 13000 && time <= 23000; // Minecraft night
    }
}