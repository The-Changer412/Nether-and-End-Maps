package io.github.bossmania.NetherandEndMaps.core.network.message;

import java.util.function.Supplier;

import com.mojang.brigadier.CommandDispatcher;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import io.github.bossmania.NetherandEndMaps.common.items.ExplorerMap;
import io.github.bossmania.NetherandEndMaps.core.init.ItemInit;
import io.github.bossmania.NetherandEndMaps.core.network.SimpleChannelNetwork;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDispatcher;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.command.CommandSource;
import net.minecraft.command.impl.LocateCommand;
import net.minecraft.command.Commands;

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
			switch (message.structureType) {
				case "fortress":
					structure = Structure.FORTRESS;
				break;
			}
			
			//get the location of the structure and send it back to the client
			BlockPos structureLocation = SEWorld.func_241117_a_(structure, player.getPosition(),100, false);
			SimpleChannelNetwork.CHANNEL.sendTo(new CreateCompassLocator(structureLocation), 
					player.connection.getNetworkManager(), 
					NetworkDirection.PLAY_TO_CLIENT);
	
			
		});
		context.setPacketHandled(true);
	}
	
	
}
