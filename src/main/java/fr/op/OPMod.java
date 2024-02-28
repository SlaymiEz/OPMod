package fr.op;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = "opmod", useMetadata=true)
public class OPMod {
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new OPCommands());
        MinecraftForge.EVENT_BUS.register(new OPFinder());
        MinecraftForge.EVENT_BUS.register(this);
    }
}
