package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumBricks;
import mrcomputerghost.runicdungeons.enums.EnumDemonic;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockSurvivalBricks extends BlockBrickStatus {

    public static final IProperty<EnumBricks> STATUS = PropertyEnum.create("bricks", EnumBricks.class);

    public static String[] registry = new String[] { "survival_plain_brick", "survival_mossy_brick",
            "survival_cracked_brick", "survival_runeplain_brick",
            "survival_runemossy_brick", "survival_runecracked_brick" };

    public static String[] localized = new String[] { "survivalBrickPlain", "survivalBrickMossy",
            "survivalBrickCracked", "survivalBrickRunePlain",
            "survivalBrickRuneMossy", "survivalBrickRuneCracked" };

    public BlockSurvivalBricks() {
        super(Material.ROCK);
        this.setUnlocalizedName("survivalRunicBrick");
        this.setRegistryName(RunicDungeons.MODID, "survival_runic_brick");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(1.5F);
        this.setResistance(10.0F);
        this.setHarvestLevel("pickaxe", 1);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STATUS, EnumBricks.PLAIN));
        RunicBlocks.BLOCKS.add(this);
    }

    @Override
    public String[] getRegistryNames() {
        return registry;
    }

    @Override
    public String[] getUnlocalizedNames() {
        return localized;
    }

    @Override
    public BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STATUS);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(STATUS).ordinal();
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(STATUS, EnumBricks.values()[meta]);
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
        for (EnumBricks variant : EnumBricks.values()) {
            list.add(new ItemStack(this, 1, variant.ordinal()));
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

}