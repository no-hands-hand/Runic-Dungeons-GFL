package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import net.minecraft.item.ItemSpade;

public class ItemRunicSteelShovel extends ItemSpade {

    public ItemRunicSteelShovel() {
        super(RunicItems.RUNIC_STEEL_TOOL);
        this.setUnlocalizedName("runic_steel_shovel");
        this.setRegistryName(RunicDungeons.MODID, "runic_steel_shovel");
        this.setCreativeTab(RunicDungeons.TAB);
        RunicItems.ITEMS.add(this);
    }

}