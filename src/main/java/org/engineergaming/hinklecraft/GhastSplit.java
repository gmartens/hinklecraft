package org.engineergaming.hinklecraft;

import java.util.Random;

import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.entity.Ghast;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;


public class GhastSplit implements Listener{
    private final Random random = new Random();

    Main plugin = Main.getPlugin(Main.class);

    private int getGhasteMeta(Ghast ghast, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer dataContainer = ghast.getPersistentDataContainer();
        return dataContainer.getOrDefault(namespacedKey, PersistentDataType.INTEGER, 0);
    }

    private void setGhastMeta(Ghast ghast, String key, int value) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer dataContainer = ghast.getPersistentDataContainer();
        dataContainer.set(namespacedKey, PersistentDataType.INTEGER, value);
    }

    @EventHandler
    public void onGhastSpawn(CreatureSpawnEvent event) {
        if(event.getEntityType() == EntityType.GHAST && (event.getSpawnReason() == SpawnReason.NATURAL || event.getSpawnReason() == SpawnReason.SPAWNER_EGG)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    setGhastMeta((Ghast) event.getEntity(), "NaturallySpawned", 1);
                }
            }.runTaskLater(plugin, 1);
        }
    }

    @EventHandler
    public void onGhastDeath(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.GHAST) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (getGhasteMeta((Ghast) event.getEntity(), "NaturallySpawned") == 1) {
                        World world = event.getEntity().getLocation().getWorld();
                        int babies = 2 +random.nextInt(3);
                        for (int i = 0; i < babies; i++) {
                            Ghast baby = (Ghast) world.spawnEntity(event.getEntity().getLocation(), EntityType.GHAST);
                            baby.getAttribute(Attribute.SCALE).addModifier(new AttributeModifier(new NamespacedKey(plugin, "GhastSmallenModifier"), -0.5, AttributeModifier.Operation.ADD_NUMBER));
                        }
                    }
                }
            }.runTaskLater(plugin, 1);
        }
    }
}
