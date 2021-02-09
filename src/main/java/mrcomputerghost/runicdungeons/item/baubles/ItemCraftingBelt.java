package mrcomputerghost.runicdungeons.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.item.RunicItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCraftingBelt extends Item implements IBauble {

    public ItemCraftingBelt(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(RunicDungeons.MODID, name);
        this.setCreativeTab(RunicDungeons.TAB);
        RunicItems.ITEMS.add(this);
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.BELT;
    }

}