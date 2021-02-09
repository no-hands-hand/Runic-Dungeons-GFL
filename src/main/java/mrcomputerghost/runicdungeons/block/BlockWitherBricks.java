package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumBricks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitherBricks extends BlockBrickStatus {

    public static final IProperty<EnumBricks> STATUS = PropertyEnum.create("bricks", EnumBricks.class);

    public static String[] registry = new String[] { "wither_plain_brick", "wither_mossy_brick",
            "wither_cracked_brick", "wither_runeplain_brick", "wither_runemossy_brick", "wither_runecracked_brick" };

    public static String[] localized = new String[] { "witherBrickPlain", "witherBrickMossy", "witherBrickCracked",
            "witherBrickRunePlain", "witherBrickRuneMossy", "witherBrickRuneCracked" };

    public BlockWitherBricks() {
        super(Material.ROCK);
        this.setUnlocalizedName("witherRunicBrick");
        this.setRegistryName(RunicDungeons.MODID, "wither_runic_brick");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(8.0F);
        this.setResistance(-1.0F);
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
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return false;
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
        world.setBlockState(pos, RunicBlocks.WITHER_BRICKS.getDefaultState());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

}