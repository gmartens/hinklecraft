package org.engineergaming.hinklecraft;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class HealthScaleListener implements Listener {
    
    private final Main plugin = Main.getPlugin(Main.class);
    private final NamespacedKey scaleModifierKey = new NamespacedKey(plugin, "HealthScaleModifier");

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        
        Player player = (Player) event.getEntity();
        
        // Schedule the scale update for after the damage is applied
        player.getServer().getScheduler().runTaskLater(plugin, () -> {
            updatePlayerScale(player);
        }, 1L);
    }

    @EventHandler
    public void onPlayerHeal(EntityRegainHealthEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        
        Player player = (Player) event.getEntity();
        
        // Schedule the scale update for after the healing is applied
        player.getServer().getScheduler().runTaskLater(plugin, () -> {
            updatePlayerScale(player);
        }, 1L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
        // Update scale when player joins (in case they had different health when they left)
        player.getServer().getScheduler().runTaskLater(plugin, () -> {
            updatePlayerScale(player);
        }, 20L); // Wait 1 second to ensure player is fully loaded
    }

    private void updatePlayerScale(Player player) {
        double currentHealth = player.getHealth();
        double maxHealth = player.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
        
        // Calculate scale based on health
        // At 0.5 hearts (1 health) = 0.5 scale (1 block tall)
        // At full health (20 health) = 1.0 scale (normal size)
        // Linear interpolation between these points
        double minHealth = 1.0; // 0.5 hearts
        double minScale = 0.5;  // 1 block tall
        double maxScale = 1.0;  // normal size
        
        double scale;
        if (currentHealth <= minHealth) {
            scale = minScale;
        } else {
            // Linear interpolation: scale = minScale + (currentHealth - minHealth) / (maxHealth - minHealth) * (maxScale - minScale)
            scale = minScale + ((currentHealth - minHealth) / (maxHealth - minHealth)) * (maxScale - minScale);
        }
        
        // Remove any existing scale modifier
        if (player.getAttribute(Attribute.SCALE).getModifier(scaleModifierKey) != null) {
            player.getAttribute(Attribute.SCALE).removeModifier(scaleModifierKey);
        }
        
        // Apply new scale modifier (subtract 1 because we want the total scale, not additive)
        double modifier = scale - 1.0;
        AttributeModifier scaleModifier = new AttributeModifier(
            scaleModifierKey, 
            modifier, 
            AttributeModifier.Operation.ADD_NUMBER
        );
        
        player.getAttribute(Attribute.SCALE).addModifier(scaleModifier);
    }
}