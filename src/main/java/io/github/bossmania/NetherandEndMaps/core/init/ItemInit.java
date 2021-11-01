package io.github.bossmania.NetherandEndMaps.core.init;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	//create the registry for items
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetherandEndMaps.MOD_ID);
	
	//register the maps and add it into the misc group
	public static final RegistryObject<Item> NETHER_FORTRESS_MAP = ITEMS.register("nether_fortress_map", 
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	
	public static final RegistryObject<Item> BASTION_REMNANT_MAP = ITEMS.register("bastion_remnant_map", 
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	
	public static final RegistryObject<Item> END_CITY_MAP = ITEMS.register("end_city_map", 
			() -> new Item(new Item.Properties().group(ItemGroup.MISC)));
	
	
	//register the blocks and add it to the block group
	public static final RegistryObject<BlockItem> NETHER_CARTOGRPAHY_TABLE = ITEMS.register("nether_cartography_table", 
			() -> new BlockItem(BlockInit.NETHER_CARTOGRPAHY_TABLE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
	
	public static final RegistryObject<BlockItem> END_CARTOGRPAHY_TABLE = ITEMS.register("end_cartography_table", 
			() -> new BlockItem(BlockInit.END_CARTOGRPAHY_TABLE.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
}
