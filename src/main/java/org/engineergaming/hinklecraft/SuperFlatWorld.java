package org.engineergaming.hinklecraft;

import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class SuperFlatWorld implements Listener {

    public static World superFlatWorld;

    private static class FlatGenerator extends ChunkGenerator {
        @Override
        public void generateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkData chunkData) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    chunkData.setBlock(x, 0, z, Material.BEDROCK);
                    chunkData.setBlock(x, 1, z, Material.DIRT);
                    chunkData.setBlock(x, 2, z, Material.DIRT);
                    chunkData.setBlock(x, 3, z, Material.GRASS_BLOCK);
                }
            }
        }

        @Override
        public BiomeProvider getDefaultBiomeProvider(WorldInfo worldInfo) {
            return new BiomeProvider() {
                @Override
                public @NotNull Biome getBiome(WorldInfo wi, int x, int y, int z) {
                    return Biome.PLAINS;
                }

                @Override
                public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
                    return List.of(Biome.PLAINS);
                }
            };
        }
    }

    SuperFlatWorld() {
        if (Bukkit.getWorld("superflat_world") == null) {
            WorldCreator creator = new WorldCreator("superflat_world");
            creator.environment(World.Environment.NORMAL);
            creator.generator(new FlatGenerator());
            superFlatWorld = Bukkit.createWorld(creator);
        } else {
            superFlatWorld = Bukkit.getWorld("superflat_world");
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if(event.getEntity().getType() != EntityType.PLAYER) return;
        Bukkit.getLogger().info(event.getCause().toString());
        if(event.getCause() != EntityDamageEvent.DamageCause.VOID) return;
        Bukkit.getLogger().info("Event!");
        Player player = (Player)event.getEntity();
        Location destination = new Location(superFlatWorld, 0, 10, 0);
        player.teleport(destination);
    }

}
