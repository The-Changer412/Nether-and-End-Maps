package io.github.bossmania.NetherandEndMaps.core.network.message;

import java.util.function.Supplier;

import com.mojang.brigadier.CommandDispatcher;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import io.github.bossmania.NetherandEndMaps.client.util.ClientAccess;
import io.github.bossmania.NetherandEndMaps.common.items.ExplorerMap;
import io.github.bossmania.NetherandEndMaps.core.init.ItemInit;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
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

public class CreateCompassLocator {
	public BlockPos structureLocation;
	
	public CreateCompassLocator() {
		
	}
	//create the constructor and store the structureLocation
	public CreateCompassLocator(BlockPos structureLocation) {
		this.structureLocation = structureLocation;
	}
	
	//encode the message
	public static void encode(CreateCompassLocator message, PacketBuffer buffer) {
		buffer.writeBlockPos(message.structureLocation);
	}
	
	//decode the message
	public static CreateCompassLocator decode(PacketBuffer buffer) {
		return new CreateCompassLocator(buffer.readBlockPos());
	}
	
	//execute function when message is sent to the client
	public static void handle(CreateCompassLocator message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			//make sure that the code is only run on the client side and send the structure to the client access to make the compass
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, 
					() -> () -> ClientAccess.createCompass(message.structureLocation));
		});
		context.setPacketHandled(true);
	}
	
	
}
