package org.engineergaming.hinklecraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class SlimeBlockBounce implements Listener {

    @EventHandler
    public void onBounce(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Vector velocity = player.getVelocity();

        // Check if player is on the ground (standing or bouncing), and moving upward
        if (velocity.getY() > 0.2 && player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.SLIME_BLOCK) {
            // Boost the bounce
            Vector boosted = velocity.clone();
            boosted.setY(velocity.getY() * 2.5); // Boost factor
            player.setVelocity(boosted);
        }
    }
}
