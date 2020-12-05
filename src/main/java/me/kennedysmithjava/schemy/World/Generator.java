package me.kennedysmithjava.schemy.World;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.material.MaterialData;

import java.util.Iterator;
import java.util.Random;

public class Generator extends ChunkGenerator {
    SchemyWorldManager manager;

    public Generator(SchemyWorldManager manager){
        this.manager = manager;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        return chunk;
    }

    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, 0 + 1, 0);
    }

    private void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
        if (result[y >> 4] == null) {
            result[y >> 4] = new byte[4096];
        }
        result[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = blkid;
    }
}
