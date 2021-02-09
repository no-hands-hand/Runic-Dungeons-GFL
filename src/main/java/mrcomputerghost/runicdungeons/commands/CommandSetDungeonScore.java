package mrcomputerghost.runicdungeons.commands;

import mrcomputerghost.runicdungeons.capabilities.CapabilityHandler;
import mrcomputerghost.runicdungeons.capabilities.IRuneDungeon;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandSetDungeonScore extends CommandBase {

    @Override
    public String getName() {
        return "setdungeonscore";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.setdungeonscore.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (sender instanceof EntityPlayer) {
            if (args.length > 0) {
                EntityPlayer player = getCommandSenderAsPlayer(sender);
                if(player.hasCapability(CapabilityHandler.capability, null)) {
                    IRuneDungeon dungeon = player.getCapability(CapabilityHandler.capability, null);
                    int score = Integer.parseInt(args[0]);
                    dungeon.setScore(score);
                    notifyCommandListener(sender, this, "commands.setdungeonscore.score", new Object[]{score});
                }
            } else {
                String text = I18n.format("commands.setdungeonscore.usage");
                notifyCommandListener(sender, this, "commands.setdungeonscore.error", new Object[]{text});
            }
        } else {
            notifyCommandListener(sender, this, "commands.dungeons.in_console");
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

}