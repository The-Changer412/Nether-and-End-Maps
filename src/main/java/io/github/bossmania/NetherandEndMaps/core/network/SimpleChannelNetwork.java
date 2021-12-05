package io.github.bossmania.NetherandEndMaps.core.network;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import io.github.bossmania.NetherandEndMaps.core.network.message.CreateCompassLocator;
import io.github.bossmania.NetherandEndMaps.core.network.message.LocateStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class SimpleChannelNetwork {
	public static final String NETWORK_VERSION = "0.1.0";
	
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(NetherandEndMaps.MOD_ID, "network"), 
			() -> NETWORK_VERSION,
			version -> version.equals(NETWORK_VERSION), 
			version -> version.equals(NETWORK_VERSION));
	
	public static void init() {
		int index = 0;
		CHANNEL.registerMessage(index++, LocateStructure.class ,LocateStructure::encode, LocateStructure::decode, LocateStructure::handle);
		CHANNEL.registerMessage(index++, CreateCompassLocator.class ,CreateCompassLocator::encode, CreateCompassLocator::decode, CreateCompassLocator::handle);
	}
}
