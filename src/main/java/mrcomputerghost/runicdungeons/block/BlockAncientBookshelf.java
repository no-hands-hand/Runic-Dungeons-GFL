package mrcomputerghost.runicdungeons.block;


import java.util.Random;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockAncientBookshelf extends Block {

    public BlockAncientBookshelf() {
        super(Material.WOOD);
        this.setUnlocalizedName("ancientBookshelf");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setRegistryName(RunicDungeons.MODID, "bookshelf");
        this.setResistance(1.5F);
        this.setHardness(10.0F);
        this.setHarvestLevel("axe", 1);
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public float getEnchantPowerBonus(World world, BlockPos pos) {
        return 0.5F;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return random.nextInt(2);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.BOOK;
    }

}
