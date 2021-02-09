package mrcomputerghost.runicdungeons.dimension;

import mrcomputerghost.runicdungeons.world.WorldGenRunicDungeon;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * @author ji_GGO
 * @date 26/12/2020
 */
public class ChunkProviderDungeon implements IChunkGenerator {

    protected static final IBlockState AIR = Blocks.AIR.getDefaultState();

    private final World world;

    public static int dungeonMaxX = 64;

    public static int dungeonMaxY = 64;

    private final Block[] cachedBlockIDs = new Block[256];

    private final byte[] cachedBlockMetadata = new byte[256];

    private NoiseGeneratorOctaves lperlinNoise1;
    private NoiseGeneratorOctaves lperlinNoise2;
    private NoiseGeneratorOctaves perlinNoise1;

    private double[] buffer;

    private Biome[] biomesForGeneration;

    double[] pnr;

    double[] ar;

    double[] br;

    public ChunkProviderDungeon(World world){
        int j;
        for (this.world = world, j = 1; j < 2; ) {
            this.cachedBlockIDs[j] = Blocks.AIR;
            this.cachedBlockMetadata[j] = 0;
            j++;
        }
        this.lperlinNoise1 = new NoiseGeneratorOctaves(new Random(), 16);
        this.lperlinNoise2 = new NoiseGeneratorOctaves(new Random(), 16);
        this.perlinNoise1 = new NoiseGeneratorOctaves(new Random(), 8);
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        Chunk chunk;
        int k;
        for (chunk = new Chunk(this.world, x, z), k = 0; k < this.cachedBlockIDs.length; ) {
            Block block = this.cachedBlockIDs[k];
            if (block != null) {
                int i = k >> 4;
                ExtendedBlockStorage extendedblockstorage = chunk.getBlockStorageArray()[i];
                if (extendedblockstorage == null) {
                    extendedblockstorage = new ExtendedBlockStorage(k, !this.world.provider.hasSkyLight());
                    chunk.getBlockStorageArray()[i] = extendedblockstorage;
                }
                for (int i1 = 0; i1 < 16; ) {
                    for (int j1 = 0; j1 < 16; ) {
                        extendedblockstorage.set(i1, k & 0xF, j1, block.getDefaultState());
                        j1++;
                    }
                    i1++;
                }
            }
            k++;
        }
        /*ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        Chunk chunk = new Chunk(this.world, chunkprimer, x, z);*/
        chunk.generateSkylightMap();
        byte[] abyte = chunk.getBiomeArray();
        this.biomesForGeneration = this.world.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte)Biome.getIdForBiome(this.biomesForGeneration[i]);
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int chunkX, int chunkZ) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return this.world.getBiome(pos).getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {}

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }

}
