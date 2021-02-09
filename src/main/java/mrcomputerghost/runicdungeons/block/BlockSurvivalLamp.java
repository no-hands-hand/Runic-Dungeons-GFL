package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockSurvivalLamp extends Block {

    public BlockSurvivalLamp() {
        super(Material.GLASS);
        this.setUnlocalizedName("survivalRunicLamp");
        this.setRegistryName(RunicDungeons.MODID, "survival_runic_lamp");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(1.5F);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightLevel(1.0F);
        RunicBlocks.BLOCKS.add(this);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return super.isOpaqueCube(state);
    }

}