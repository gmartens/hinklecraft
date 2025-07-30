package org.engineergaming.hinklecraft;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.scheduler.BukkitRunnable;

import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.Random;

public class MobChanger implements Listener {
    private final Random random = new Random();

    Main plugin = Main.getPlugin(Main.class);

    private boolean isNight(World world) {
        long time = world.getTime();
        return time >= 13000 && time <= 23000; // Minecraft night
    }

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
                }.runTaskLater(plugin, 1L);
            }
        }
    }

    @EventHandler
    public void onGolemSpawn(CreatureSpawnEvent event) {
        if(event.getEntityType() == EntityType.IRON_GOLEM) {
            World world = event.getEntity().getWorld();
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(event.getSpawnReason() == SpawnReason.BUILD_IRONGOLEM) {
                        event.getEntity().remove();
                        world.spawn(event.getLocation(), Wither.class, (Wither w) -> {
                            w.customName(MiniMessage.miniMessage().deserialize("<bold><red>ACTIVATED HINKLETRON REACTOR"));
                        });
                    }
                    if(event.getSpawnReason() == SpawnReason.VILLAGE_DEFENSE || event.getSpawnReason() == SpawnReason.NATURAL) {
                        event.getEntity().remove();
                        world.spawn(event.getLocation(), Wither.class, (Wither w) -> {
                            w.setAI(false);
                            w.customName(MiniMessage.miniMessage().deserialize("<bold><green>DEACTIVATED HINKLETRON REACTOR"));
                        });
                    }
                }
            }.runTaskLater(plugin, 1L);
        }
    }

    @EventHandler
    public void onWitherSpawn(CreatureSpawnEvent event) {
        if(event.getEntityType() == EntityType.WITHER && event.getSpawnReason() == SpawnReason.BUILD_WITHER) {
            World world = event.getEntity().getWorld();
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getEntity().remove();
                    world.spawn(event.getLocation(), Snowman.class, (Snowman s) -> {
                        s.customName(MiniMessage.miniMessage().deserialize("<bold><blue>zinkle"));
                    });
                }
            }.runTaskLater(plugin, 1L);
        }
    }

}