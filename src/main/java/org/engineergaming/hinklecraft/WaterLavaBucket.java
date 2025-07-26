package org.engineergaming.hinklecraft;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class WaterLavaBucket implements Listener {
    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent event) {
        ItemStack bucket = event.getItemStack();
        Player player = event.getPlayer();
        EquipmentSlot hand = event.getHand();
        
        if(bucket.getType() == Material.WATER_BUCKET) {
            player.getInventory().setItem(hand, new ItemStack(Material.LAVA_BUCKET, 1));
            return;
        }
        if(bucket.getType() == Material.LAVA_BUCKET) {
            player.getInventory().setItem(hand, new ItemStack(Material.WATER_BUCKET, 1));
            return;
        }
    }
}
