package org.engineergaming.hinklecraft;

import java.util.Random;

import org.bukkit.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class FoodPotionRandomizer implements Listener {
    Random random = new Random();

    @EventHandler
    public void onFoodEat(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        Registry<PotionEffectType> effectRegistry = RegistryAccess.registryAccess().getRegistry(RegistryKey.MOB_EFFECT);
        PotionEffectType effectType = PotionEffectType.ABSORPTION;
        int i = 0;
        int target = random.nextInt(effectRegistry.size());
        for(PotionEffectType effect : effectRegistry) {
            if(i == target) {
                effectType = effect;
                break;
            }
            i++;
        }
        player.addPotionEffect(new PotionEffect(effectType, 10*20, 0));
        player.sendMessage(MiniMessage.miniMessage().deserialize("<green><bold>[HinkleCraft] <yellow>Hungry boy! <italic>*PATS BIG BELLY*</italic> Your tummy must be so full! <italic>*BURPS*</italic>"));
    }
    
}
