package mrcomputerghost.runicdungeons.block;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.enums.EnumBricks;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class BlockObsidianBricks extends BlockBrickStatus {

    public static final IProperty<EnumBricks> STATUS = PropertyEnum.create("bricks", EnumBricks.class);

    public static String[] registry = new String[] { "obsidian_plain_brick", "obsidian_mossy_brick",
            "obsidian_cracked_brick", "obsidian_runeplain_brick",
            "obsidian_runemossy_brick", "obsidian_runecracked_brick" };

    public static String[] localized = new String[] { "obsidianBrickPlain", "obsidianBrickMossy",
            "obsidianBrickCracked", "obsidianBrickRunePlain",
            "obsidianBrickRuneMossy", "obsidianBrickRuneCracked" };

    public BlockObsidianBricks() {
        super(Material.ROCK);
        this.setUnlocalizedName("obsidianRunicBrick");
        this.setRegistryName(RunicDungeons.MODID, "obsidian_runic_brick");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setHardness(8.0F);
        this.setResistance(16.0F);
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