package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.item.ItemPickaxe;

public class ItemRunicSteelPickaxe extends ItemPickaxe {

    public ItemRunicSteelPickaxe() {
        super(RunicItems.RUNIC_STEEL_TOOL);
        this.setUnlocalizedName("runic_steel_pickaxe");
        this.setRegistryName(RunicDungeons.MODID, "runic_steel_pickaxe");
        this.setCreativeTab(RunicDungeons.TAB);
        RunicItems.ITEMS.add(this);
    }

}