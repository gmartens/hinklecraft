package org.engineergaming.hinklecraft;

import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.event.EventHandler;
import org.bukkit.util.Vector;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Silverfish;
import org.bukkit.inventory.ItemStack;

public class GoldDropListener implements Listener{
    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void GoldDrop(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.GOLD_INGOT) {
            Vector vel = event.getItemDrop().getVelocity();
            Location loc = event.getItemDrop().getLocation();
            World world = event.getItemDrop().getWorld();
            new BukkitRunnable() {
                @Override
                public void run() {
                    event.getItemDrop().remove();
                    world.spawn(loc, Silverfish.class, (Silverfish s) -> {
                        s.setVelocity(vel);
                        s.customName(MiniMessage.miniMessage().deserialize("<bold><yellow>Loot Bug"));
                        s.setGlowing(true);
                        s.getEquipment().setItemInMainHand(new ItemStack(Material.GOLD_NUGGET, 9), true);
                        s.getEquipment().setItemInMainHandDropChance(1F);
                    });
                }
            }.runTaskLater(plugin, 1L);
        }
    }
}
