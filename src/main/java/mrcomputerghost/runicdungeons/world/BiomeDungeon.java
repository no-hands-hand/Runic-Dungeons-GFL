package mrcomputerghost.runicdungeons.world;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeDungeon extends Biome {

    public BiomeDungeon() {
        super(new Biome.BiomeProperties("Dungeon")
                .setBaseHeight(0.2F)
                .setHeightVariation(0.5F));
        this.topBlock = Blocks.AIR.getDefaultState();
        this.fillerBlock = Blocks.AIR.getDefaultState();
        this.spawnableMonsterList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.setRegistryName(RunicDungeons.MODID,"dungeon");
        //setColor(747474);
    }

}
