package mrcomputerghost.runicdungeons.world;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.dimension.ChunkProviderDungeon;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * @author ji_GGO
 * @date 26/12/2020
 */
public class TerribleDungeon implements IWorldGenerator {

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() == RunicDungeons.ID) {
            Random random = new Random(System.currentTimeMillis());
            int x = chunkX * 16;
            int z = chunkZ * 16;
            if (ChunkProviderDungeon.dungeonMaxX == -1 && ChunkProviderDungeon.dungeonMaxY == -1) {
                WorldGenRunicDungeon.generate(world, random, x, 0, z);
            } else if (chunkX > 0 - ChunkProviderDungeon.dungeonMaxX && chunkZ < ChunkProviderDungeon.dungeonMaxX &&
                    chunkZ > 0 - ChunkProviderDungeon.dungeonMaxY && chunkZ < ChunkProviderDungeon.dungeonMaxY) {
                WorldGenRunicDungeon.generate(world, random, x, 0, z);
            }
        }
    }

}
