package mrcomputerghost.runicdungeons.creativetab;

import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CreativeTab extends CreativeTabs {

    public CreativeTab() {
        super("runicdungeons");
    }

    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(RunicItems.KEY);
    }

}