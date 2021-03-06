package io.github.bossmania.NetherandEndMaps.common.items;

import io.github.bossmania.NetherandEndMaps.core.network.SimpleChannelNetwork;
import io.github.bossmania.NetherandEndMaps.core.network.message.LocateStructure;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ExplorerMap extends Item{
    
    //check if right click has been pressed to prevent double right click
	boolean rightClicked = false;
	String type = "";

	//create the class
	public ExplorerMap(Properties properties, String type) {
		super(properties);
		this.type = type;
	}
	
	//function event that gets execute when item is right clicked
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		
//		playerIn.sendMessage(new TranslationTextComponent("testing123"), playerIn.getGameProfile().getId());
		
		//check if right click has been pressed
		if (rightClicked == false) {
			rightClicked = true;
			
			//get the dimension that the player is currently in
			String[] dimensionList = playerIn.world.getDimensionKey().toString().split("/ ");
			String dimension = dimensionList[1].substring(0, dimensionList[1].length() -1);
			
			//check if the player is in the right dimension
			if (type.equals("fortress") || type.equals("bastion_remnant")) {
				if (dimension.equals("minecraft:the_nether") == false) {
					playerIn.sendMessage(new TranslationTextComponent("You can't use the map in this dimension. This map can only be used in the nether."), playerIn.getGameProfile().getId());
				} else {
					//request a compass with the structure coords to the server
					SimpleChannelNetwork.CHANNEL.sendToServer(new LocateStructure(type));
				}
			} else if (type.equals("endcity")) {
				if (dimension.equals("minecraft:the_end") == false) {
					playerIn.sendMessage(new TranslationTextComponent("You can't use the map in this dimension. This map can only be used in the end."), playerIn.getGameProfile().getId());
				} else {
					//request a compass with the structure coords to the server
					SimpleChannelNetwork.CHANNEL.sendToServer(new LocateStructure(type));
				}
			}
			
			//send a success to the item
			return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
			
		} else {
			//send a fail to the item cause right click has been pressed
			rightClicked = false;
			return ActionResult.resultFail(playerIn.getHeldItem(handIn));
		}
	}
}