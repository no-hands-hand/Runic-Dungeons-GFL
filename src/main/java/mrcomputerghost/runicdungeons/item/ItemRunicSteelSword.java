package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.item.ItemSword;

public class ItemRunicSteelSword extends ItemSword {

    public ItemRunicSteelSword() {
        super(RunicItems.RUNIC_STEEL_TOOL);
        this.setUnlocalizedName("runic_steel_sword");
        this.setRegistryName(RunicDungeons.MODID, "runic_steel_sword");
        this.setCreativeTab(RunicDungeons.TAB);
        RunicItems.ITEMS.add(this);
    }

}