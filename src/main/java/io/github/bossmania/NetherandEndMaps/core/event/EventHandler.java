package io.github.bossmania.NetherandEndMaps.core.event;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid=NetherandEndMaps.MOD_ID, bus=Bus.FORGE, value=Dist.CLIENT)
public class EventHandler {
	
	@SubscribeEvent
	public static void onPlayerInventory(final TickEvent.PlayerTickEvent event) {
		if (!event.player.world.isRemote) {
			return;
		}
		//save the player in a varabile
		PlayerEntity playerIn =  event.player;
		
		//iter through the inventory to find compasses
		for (int i = 0; i < playerIn.inventory.mainInventory.size(); i++) {
			ItemStack item = playerIn.inventory.mainInventory.get(i);
			if (item.isItemEqual(new ItemStack(Items.COMPASS))) {
				//get the nbt 
				CompoundNBT nbt = item.getTag();
				//check if the compass is tracking
				try {
					if (nbt.getBoolean("LodestoneTracked") == true) {
						//get the coords from the name
						String name = item.getDisplayName().getString();
						String[] names = name.split("X: ");
						String[] coords = names[1].split(" / Z: ");
						int z = Integer.parseInt(coords[1]);
						Vector3d coord = new Vector3d(Integer.parseInt(coords[0]), 0, z);

						//put the corrds into the compass
						nbt.put("LodestonePos", NBTUtil.writeBlockPos(new BlockPos(coord)));
						item.setTag(nbt);
						
					}
				} catch (Exception e) {
					
				}
			}
		}
	}
}