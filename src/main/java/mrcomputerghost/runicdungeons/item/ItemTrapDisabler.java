package mrcomputerghost.runicdungeons.item;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.block.RunicBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemTrapDisabler extends Item {

    public ItemTrapDisabler() {
        this.setUnlocalizedName("trap_remover");
        this.setCreativeTab(RunicDungeons.TAB);
        this.setRegistryName(RunicDungeons.MODID, "trap_remover");
        this.setMaxStackSize(1);
        RunicItems.ITEMS.add(this);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.getBlockState(pos).getBlock() == RunicBlocks.SPIKE_TRAP) {
            world.setBlockState(pos, RunicBlocks.BRICKS.getDefaultState());
            return EnumActionResult.SUCCESS;
        }

        return super.onItemUse(player, world, pos, hand, facing, hitX, hitY, hitZ);
    }

}