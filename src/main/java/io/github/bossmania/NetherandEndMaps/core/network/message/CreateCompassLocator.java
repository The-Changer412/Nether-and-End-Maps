package io.github.bossmania.NetherandEndMaps.core.network.message;

import java.util.function.Supplier;

import io.github.bossmania.NetherandEndMaps.client.util.ClientAccess;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

public class CreateCompassLocator {
	public BlockPos structureLocation;
	public String structureType;
	
	public CreateCompassLocator() {
		
	}
	//create the constructor and store the structureLocation
	public CreateCompassLocator(BlockPos structureLocation, String structureType) {
		this.structureLocation = structureLocation;
		this.structureType = structureType;
	}
	
	//encode the message
	public static void encode(CreateCompassLocator message, PacketBuffer buffer) {
		buffer.writeBlockPos(message.structureLocation);
		buffer.writeString(message.structureType);
	}
	
	//decode the message
	public static CreateCompassLocator decode(PacketBuffer buffer) {
		return new CreateCompassLocator(buffer.readBlockPos(), buffer.readString());
	}
	
	//execute function when message is sent to the client
	public static void handle(CreateCompassLocator message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			//make sure that the code is only run on the client side and send the structure to the client access to make the compass
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, 
					() -> () -> ClientAccess.createCompass(message.structureLocation, message.structureType));
		});
		context.setPacketHandled(true);
	}
	
	
}
