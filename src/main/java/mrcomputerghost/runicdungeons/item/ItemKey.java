package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemKey extends Item {

    public ItemKey() {
        this.setUnlocalizedName("key");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setRegistryName(RunicDungeons.MODID,"key");
        this.setMaxStackSize(1);
        this.setMaxDamage(2);
        this.setContainerItem(this);
        RunicItems.ITEMS.add(this);
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack) {
        return false;
    }

}