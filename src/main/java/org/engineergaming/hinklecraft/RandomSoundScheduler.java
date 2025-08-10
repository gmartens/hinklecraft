package org.engineergaming.hinklecraft;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import org.bukkit.Bukkit;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class RandomSoundScheduler {
    
    private final Plugin plugin;
    private final Random random = new Random();
    
    public RandomSoundScheduler(Plugin plugin) {
        this.plugin = plugin;
    }
    
    public void startScheduler(long period) {
        new BukkitRunnable() {
            @Override
            public void run() {
                playRandomSoundToAllPlayers();
            }
        }.runTaskTimer(plugin, period*20, period*20);
    }
    
    private void playRandomSoundToAllPlayers() {
        Registry<Sound> soundRegistry = RegistryAccess.registryAccess().getRegistry(RegistryKey.SOUND_EVENT);

        Sound randomSound = Sound.ENTITY_PIG_AMBIENT;

        int i = 0;
        int target = random.nextInt(soundRegistry.size());
        for(Sound sound : soundRegistry) {
            if(i == target) {
                randomSound = sound;
                break;
            }
            i++;
        }

        float pitch = 0.5f + random.nextFloat() * 1.5f; // Random pitch between 0.5 and 2.0
        
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), randomSound, 1, pitch);
        }
    }
}