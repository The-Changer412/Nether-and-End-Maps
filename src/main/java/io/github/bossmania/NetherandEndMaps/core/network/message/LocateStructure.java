package io.github.bossmania.NetherandEndMaps.core.network.message;

import java.util.function.Supplier;

import io.github.bossmania.NetherandEndMaps.core.network.SimpleChannelNetwork;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

public class LocateStructure {
	public String structureType;
	
	public LocateStructure() {
		
	}
	//create the constructor and store the structureType
	public LocateStructure(String structureType) {
		this.structureType = structureType;
	}
	
	//encode the message
	public static void encode(LocateStructure message, PacketBuffer buffer) {
		buffer.writeString(message.structureType);
	}
	
	//decode the message
	public static LocateStructure decode(PacketBuffer buffer) {
		return new LocateStructure(buffer.readString());
	}
	
	//execute function when message is sent to server
	public static void handle(LocateStructure message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			//get the player and the server
			ServerPlayerEntity player = context.getSender();
			ServerWorld SEWorld = player.getServerWorld();
			
			//check what type of structure is being called
			Structure<NoFeatureConfig> structure = null;
			Structure<VillageConfig> structureVillageConfig = null;
			switch (message.structureType) {
				case "fortress":
					structure = Structure.FORTRESS;
				break;
				case "bastion_remnant":
					structureVillageConfig = Structure.BASTION_REMNANT;
				break;
				case "endcity":
				    structure = Structure.END_CITY;
				break;
			}
			
			//get the location of the structure based on the type of map
			BlockPos structureLocation = null;
			if (structure != null) {
				structureLocation = SEWorld.func_241117_a_(structure, player.getPosition(), 100, false);
			} else if (structureVillageConfig != null) {
				structureLocation = SEWorld.func_241117_a_(structureVillageConfig, player.getPosition(), 100, false);
			}
			
			//send the structure location to the client
			SimpleChannelNetwork.CHANNEL.sendTo(new CreateCompassLocator(structureLocation, message.structureType), 
					player.connection.getNetworkManager(), 
					NetworkDirection.PLAY_TO_CLIENT);
		});
		
		//tell the server that the packet was handle properly
		context.setPacketHandled(true);
	}
	
	
}
