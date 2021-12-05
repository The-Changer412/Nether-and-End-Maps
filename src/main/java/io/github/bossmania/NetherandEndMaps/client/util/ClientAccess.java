package io.github.bossmania.NetherandEndMaps.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;

@SuppressWarnings("resource")
public class ClientAccess {
	public static void createCompass(BlockPos structureLocation) {
		ItemStack structureCompass = new ItemStack(Items.COMPASS);
		
		CompoundNBT nbtTag = structureCompass.getTag();
		if (nbtTag == null) {
			nbtTag = new CompoundNBT();
			structureCompass.setTag(nbtTag);
		}
		
		//add the NBT data of the compass and point it to the structure
		nbtTag.putBoolean("LodestoneTracked", true);
		nbtTag.putString("LodestoneDimension", "minecraft:the_nether");
		nbtTag.put("LodestonePos", NBTUtil.writeBlockPos(structureLocation));
		structureCompass.setTag(nbtTag);
		
		//give the item to the player and consume the map
		Minecraft.getInstance().player.inventory.addItemStackToInventory(structureCompass);
		Minecraft.getInstance().player.inventory.getCurrentItem().shrink(1);
	}
}
