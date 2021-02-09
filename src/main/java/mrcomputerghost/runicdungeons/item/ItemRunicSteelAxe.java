package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.item.ItemAxe;

public class ItemRunicSteelAxe extends ItemAxe {

    public ItemRunicSteelAxe() {
        super(RunicItems.RUNIC_STEEL_TOOL, 8.0F, -3.0F);
        this.setUnlocalizedName("runic_steel_axe");
        this.setRegistryName(RunicDungeons.MODID, "runic_steel_axe");
        this.setCreativeTab(RunicDungeons.TAB);
        RunicItems.ITEMS.add(this);
    }

}