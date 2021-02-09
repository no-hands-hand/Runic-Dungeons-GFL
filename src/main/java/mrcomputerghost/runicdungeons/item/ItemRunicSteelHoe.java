package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.item.ItemHoe;

public class ItemRunicSteelHoe extends ItemHoe {

    public ItemRunicSteelHoe() {
        super(RunicItems.RUNIC_STEEL_TOOL);
        this.setUnlocalizedName("runic_steel_hoe");
        this.setRegistryName(RunicDungeons.MODID, "runic_steel_hoe");
        this.setCreativeTab(RunicDungeons.TAB);
        RunicItems.ITEMS.add(this);
    }

}