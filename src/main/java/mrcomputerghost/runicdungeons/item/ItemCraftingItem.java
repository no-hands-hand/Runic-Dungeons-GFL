package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCraftingItem extends Item {

    public ItemCraftingItem(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(RunicDungeons.MODID, name);
        this.setCreativeTab(RunicDungeons.TAB);
        RunicItems.ITEMS.add(this);
    }

    @Override
    public boolean hasEffect(ItemStack stack) {
        return this.getUnlocalizedName().contains("crystal");
    }

}