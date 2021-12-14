package io.github.bossmania.NetherandEndMaps.core.init;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import io.github.bossmania.NetherandEndMaps.common.items.ExplorerMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	//create the registry for items
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetherandEndMaps.MOD_ID);
	
	//register the maps and add it into the misc group
	public static final RegistryObject<ExplorerMap> NETHER_FORTRESS_MAP = ITEMS.register("nether_fortress_map", 
			() -> new ExplorerMap(new Item.Properties().group(ItemGroup.MISC), "fortress"));
	
	public static final RegistryObject<ExplorerMap> BASTION_REMNANT_MAP = ITEMS.register("bastion_remnant_map", 
			() -> new ExplorerMap(new Item.Properties().group(ItemGroup.MISC), "bastion_remnant"));
	
	public static final RegistryObject<ExplorerMap> END_CITY_MAP = ITEMS.register("end_city_map", 
			() -> new ExplorerMap(new Item.Properties().group(ItemGroup.MISC), "endcity"));
}
