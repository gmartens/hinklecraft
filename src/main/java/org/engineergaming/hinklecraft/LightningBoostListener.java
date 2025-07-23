package org.engineergaming.hinklecraft;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.event.weather.LightningStrikeEvent.Cause;
import org.bukkit.scheduler.BukkitRunnable;

public class LightningBoostListener implements Listener {
    @EventHandler
    public void onLightningStrike(LightningStrikeEvent event) {
        if(event.getCause() == Cause.WEATHER) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 100; i++) { 
                        event.getWorld().strikeLightning(event.getLightning().getLocation());
                    }
                }
            }.runTaskLater(Main.getPlugin(Main.class), 1L);
        }
    }
    
}
