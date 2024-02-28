package fr.op;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;


public class OPCommands extends CommandBase {
    public static String prefix = EnumChatFormatting.YELLOW + "[OPFinder] ";

    @Override
    public String getCommandName() {
        return "opConfig";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Ez";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        final String commandError = "On dirait que tu t'es trompé dans la commande";
        if (args.length == 2){
             if (args[0].equals("set")){
                 if (args[1].equals("on")){
                    OPFinder.activated = true;
                    chatLog(prefix + EnumChatFormatting.WHITE + "Tu as bien " + EnumChatFormatting.GREEN + "ACTIVÉ" + EnumChatFormatting.WHITE + " le OPFinder.");
                 } else if (args[1].equals("off")){
                     OPFinder.activated = false;
                     chatLog(prefix + EnumChatFormatting.WHITE + "Tu as bien " + EnumChatFormatting.RED + "DESACTIVÉ" + EnumChatFormatting.WHITE + " le OPFinder.");
                 } else {
                     throw new CommandException(commandError);
                 }
             } else if (args[0].equals("reset")) {
                 if (args[1].equals("found")){
                     OPFinder.found = false;
                     OPFinder.logged = false;
                     chatLog(prefix + EnumChatFormatting.WHITE + "Tu as bien " + EnumChatFormatting.GREEN + "RESET" + EnumChatFormatting.WHITE + " la variable " + EnumChatFormatting.YELLOW + "found " + EnumChatFormatting.WHITE + "!");
                 } else if (args[1].equals("first")) {
                    OPFinder.first = false;
                    chatLog(prefix + EnumChatFormatting.WHITE + "Tu as bien " + EnumChatFormatting.GREEN + "RESET" + EnumChatFormatting.WHITE + " la variable " + EnumChatFormatting.YELLOW + "first " + EnumChatFormatting.WHITE + "!");
                 }
            } else {
                 throw new CommandException(commandError);
             }
        } else {
            throw new CommandException(commandError);
        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    private void chatLog(String str){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
    }
}
