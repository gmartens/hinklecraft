package org.engineergaming.hinklecraft;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class CrouchSoundListener implements Listener {

    @EventHandler
    public void onPlayerCrouch(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        
        // Only play sound when starting to crouch, not when stopping
        if (event.isSneaking()) {
            // Example: player.playSound(player.getLocation(), "custom.crouch_sound", 1.0f, 1.0f);
            player.playSound(player.getLocation(), "hinklecraft.fart", 1.0f, 1.0f);
        }
    }
}