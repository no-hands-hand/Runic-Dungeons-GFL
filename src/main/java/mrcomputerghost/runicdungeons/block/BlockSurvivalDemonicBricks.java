package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumDemonic;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockSurvivalDemonicBricks extends BlockBrickStatus {

    public static final IProperty<EnumDemonic> STATUS = PropertyEnum.create("bricks", EnumDemonic.class);

    public static String[] registry = new String[] { "survival_plain_demonic", "survival_runic_demonic" };

    public static String[] localized = new String[] { "survivalDemonicBrickPlain", "survivalDemonicBrickRunic"};

    public BlockSurvivalDemonicBricks() {
        super(Material.ROCK);
        this.setUnlocalizedName("survivalDemonicBrick");
        this.setRegistryName(RunicDungeons.MODID, "survival_demonic_brick");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(1.5F);
        this.setResistance(12.0F);
        this.setHarvestLevel("pickaxe", 1);
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
    public int damageDropped(IBlockState state) {
        return this.getMetaFromState(state);
    }

}