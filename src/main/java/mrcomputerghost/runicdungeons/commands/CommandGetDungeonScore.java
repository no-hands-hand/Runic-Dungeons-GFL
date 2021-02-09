package mrcomputerghost.runicdungeons.commands;

import mrcomputerghost.runicdungeons.capabilities.CapabilityHandler;
import mrcomputerghost.runicdungeons.capabilities.IRuneDungeon;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandGetDungeonScore extends CommandBase {

    @Override
    public String getName() {
        return "dungeonscore";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/dungeonscore";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = getCommandSenderAsPlayer(sender);
            if(player.hasCapability(CapabilityHandler.capability, null)) {
                IRuneDungeon dungeon = player.getCapability(CapabilityHandler.capability, null);
                notifyCommandListener(sender, this, "commands.getdungeonscore.score",
                        new Object[]{dungeon.getScore()});
            }
        } else {
            notifyCommandListener(sender, this, "commands.dungeons.in_console");
        }
    }

}