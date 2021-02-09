package mrcomputerghost.runicdungeons.dimension;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.gen.ChunkGeneratorEnd;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * @author ji_GGO
 * @date 26/12/2020
 */
public class WorldProviderDungeon extends WorldProvider {

    @Override
    public void init() {
        this.biomeProvider = new BiomeProviderSingle(RunicDungeons.DUNGEON);
        //this.hasSkyLight = false;
        this.nether = false;
    }

    @Override
    protected void generateLightBrightnessTable() {
        float f = 0.15F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * 1.0F + f;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSkyColored() {
        return false;
    }

    @Override
    public IChunkGenerator createChunkGenerator() {
        return new ChunkProviderTest(this.world, this.world.getWorldInfo().isMapFeaturesEnabled(), this.world.getSeed(), this.getSpawnCoordinate());
    }

    @Override
    public boolean isSurfaceWorld() {
        return false;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z) {
        return true;
    }

    @Override
    public float calculateCelestialAngle(long worldTime, float partialTicks) {
        return 1.0f;
    }

    @Nullable
    @Override
    public float[] calcSunriseSunsetColors(float celestialAngle, float partialTicks) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getCloudHeight() {
        return 10.0F;
    }

    @Override
    public boolean canRespawnHere() {
        return true;
    }

    @Override
    public boolean doesXZShowFog(int x, int z) {
        return false;
    }

    @Override
    public DimensionType getDimensionType() {
        return RunicDungeons.dimension;
    }

}