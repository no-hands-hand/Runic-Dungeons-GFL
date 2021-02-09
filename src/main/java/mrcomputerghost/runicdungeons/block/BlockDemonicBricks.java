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
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

/**
 * @author ji_GGO
 * @date 27/12/2020
 */
public class BlockDemonicBricks extends BlockBrickStatus {

    public static final IProperty<EnumDemonic> STATUS = PropertyEnum.create("bricks", EnumDemonic.class);

    public static String[] registry = new String[] { "plain_demonic", "runic_demonic" };

    public static String[] localized = new String[] { "demonicBrickPlain", "demonicBrickRunic"};

    public BlockDemonicBricks() {
        super(Material.ROCK);
        this.setUnlocalizedName("demonicBrick");
        this.setRegistryName(RunicDungeons.MODID, "demonic_brick");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(-1.0F);
        this.setResistance(-1.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(STATUS, EnumDemonic.PLAIN));
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
        return getDefaultState().withProperty(STATUS, EnumDemonic.values()[meta]);
    }

    @Override
    public void getSubBlocks(CreativeTabs creativeTab, NonNullList<ItemStack> list) {
        for (EnumDemonic variant : EnumDemonic.values()) {
            list.add(new ItemStack(this, 1, variant.ordinal()));
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {}

    @Override
    public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
        return false;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return false;
    }

}