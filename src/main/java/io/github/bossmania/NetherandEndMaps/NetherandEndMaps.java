package io.github.bossmania.NetherandEndMaps;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.bossmania.NetherandEndMaps.core.init.BlockInit;
import io.github.bossmania.NetherandEndMaps.core.init.ItemInit;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NetherandEndMaps.MOD_ID)
public class NetherandEndMaps {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "nemaps";

    public NetherandEndMaps() {
        // Register the setup method for modloading
    	IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        
        //register the inits
        ItemInit.ITEMS.register(bus);
        BlockInit.BLOCKS.register(bus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    	
    }
}
