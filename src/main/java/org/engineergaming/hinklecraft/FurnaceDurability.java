package org.engineergaming.hinklecraft;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import io.papermc.paper.datacomponent.DataComponentTypes;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class FurnaceDurability implements Listener {
    private Plugin plugin = Main.getPlugin(Main.class);

    private int getFurnaceMeta(Furnace furnace, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer dataContainer = furnace.getPersistentDataContainer();
        return dataContainer.getOrDefault(namespacedKey, PersistentDataType.INTEGER, 0);
    }

    private void setFurnaceMeta(Furnace furnace, String key, int value) {
        NamespacedKey namespacedKey = new NamespacedKey(plugin, key);
        PersistentDataContainer dataContainer = furnace.getPersistentDataContainer();
        dataContainer.set(namespacedKey, PersistentDataType.INTEGER, value);
        furnace.update();
    }

    @EventHandler
    public void onFurnaceFinishItem(FurnaceSmeltEvent event) {
        Block block = event.getBlock();
        World world = block.getWorld();

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!(block.getState() instanceof Furnace furnace)) return;
                setFurnaceMeta(furnace, "smelt_count", getFurnaceMeta(furnace, "smelt_count") + 1);
                if(getFurnaceMeta(furnace, "smelt_count") >= 32) {
                    world.playEffect(block.getLocation(), Effect.STEP_SOUND, block.getType());
                    block.setType(Material.AIR);

                    ItemStack brokenFurnaceComponent = new ItemStack(Material.COBBLESTONE, 1);
                    for(int i = 1; i <= 8; i++) {
                        brokenFurnaceComponent.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<green>Broken Furnace Component " + i));
                        world.dropItemNaturally(block.getLocation(), brokenFurnaceComponent);
                    }
                }
            }
        }.runTaskLater(plugin, 1); // delaying by one tick is necessary otherwise the craft can't complete in time!

    }
    
}
