// The idea with this one is that when someone tries to mine diamonds it just kills them
// My thought is with explosions but it can proabably be updated to whatever

package org.engineergaming.hinklecraft;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class DiamondBreakListener implements Listener {
    @EventHandler
    public void onDiamondBreak(BlockBreakEvent event) {
        Block minedBlock = event.getBlock()
        if (minedBlock == Material.DIAMOND_BLOCK) {

        }
    }
}