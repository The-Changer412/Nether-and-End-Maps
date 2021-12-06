package io.github.bossmania.NetherandEndMaps.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

@SuppressWarnings("resource")
public class ClientAccess {
	public static void createCompass(BlockPos structureLocation, String structureType) {
		
		//get the player
		ClientPlayerEntity PlayerIn = Minecraft.getInstance().player;
		
		//create the compass
		ItemStack structureCompass = new ItemStack(Items.COMPASS);
		
		//get the current dimension that the player is in
		String[] dimensionList = PlayerIn.world.getDimensionKey().toString().split("/ ");
		String dimension = dimensionList[1].substring(0, dimensionList[1].length() -1);
		
		//get or make the NBT tag
		CompoundNBT nbtTag = structureCompass.getTag();
		if (nbtTag == null) {
			nbtTag = new CompoundNBT();
			structureCompass.setTag(nbtTag);
		}
		
		//add the NBT data of the compass and point it to the structure
		nbtTag.putBoolean("LodestoneTracked", true);
		if (dimension.equals("minecraft:the_nether") == true) {
			nbtTag.putString("LodestoneDimension", "minecraft:the_nether");
		} else if (dimension.equals("minecraft:the_end") == true) {
			nbtTag.putString("LodestoneDimension", "minecraft:the_end");
		}
		nbtTag.put("LodestonePos", NBTUtil.writeBlockPos(structureLocation));
		
		structureCompass.setTag(nbtTag);
		
		
		if (structureType.equals("fortress") == true) {
			structureCompass.setDisplayName(new TranslationTextComponent("Nether Fortress Compass"));
		} else if (structureType.equals("bastion_remnant") == true) {
			structureCompass.setDisplayName(new TranslationTextComponent("Bastion Remnant Compass"));
		} else if (structureType.equals("endcity") == true) {
			structureCompass.setDisplayName(new TranslationTextComponent("End City Compass"));
		}
		
		
		
		//give the item to the player
		PlayerIn.inventory.addItemStackToInventory(structureCompass);
	}
}
