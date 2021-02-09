package mrcomputerghost.runicdungeons.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * @author ji_GGO
 * @date 31/12/2020
 */
public abstract class BlockBrickStatus extends Block {

    public BlockBrickStatus(Material material) {
        super(material);
    }

    public abstract String[] getRegistryNames();

    public abstract String[] getUnlocalizedNames();

}
