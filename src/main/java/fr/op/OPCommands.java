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
        final String commandError = "On dirait que tu t'es trompé dans la commande, essaie \"/opConfig help\"";
        if (args.length == 2){
             if (args[0].equals("set")){
                 if (args[1].equals("on")){
                    OPFinder.activated = true;
                    chatLog(prefix + c("white") + "Tu as bien " + c("green") + "ACTIVÉ" + c("white") + " le OPFinder.");
                 } else if (args[1].equals("off")){
                     OPFinder.activated = false;
                     chatLog(prefix + c("white") + "Tu as bien " + c("red") + "DESACTIVÉ" + c("white") + " le OPFinder.");
                 } else {
                     throw new CommandException(commandError);
                 }
             } else if (args[0].equals("reset")) {
                 if (args[1].equals("found")){
                     OPFinder.found = false;
                     OPFinder.logged = false;
                     chatLog(prefix + c("white") + "Tu as bien " + c("green") + "RESET" + c("white") + " la variable " + c("yellow") + "found " + c("white") + "!");
                 } else if (args[1].equals("first")) {
                    OPFinder.first = false;
                    chatLog(prefix + c("white") + "Tu as bien " + c("green") + "RESET" + c("white") + " la variable " + c("yellow") + "first " + c("white") + "!");
                 }
            } else {
                 throw new CommandException(commandError);
             }
        } else if(args.length == 1) {
            if (args[0].equals("help")){
                chatLog(prefix + c("white") + c("italic") + "\"/opConfig set on\" " + c("reset") + "pour activer le finder");
                chatLog(prefix + c("white") + c("italic") + "\"/opConfig set off\" " + c("reset") + "pour désactiver le finder");
        } else {
                throw new CommandException(commandError);
            }
        } else {
            throw new CommandException(commandError);
        }
    }

    private EnumChatFormatting c(String s){
        return EnumChatFormatting.valueOf(s.toUpperCase());
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    private void chatLog(String str){
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(str));
    }
}
