package org.engineergaming.hinklecraft;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitRunnable;

public class GhastSplit {
    private final Random random = new Random();

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onGhastSpawn(CreatureSpawnEvent event) {
        
    }
}
