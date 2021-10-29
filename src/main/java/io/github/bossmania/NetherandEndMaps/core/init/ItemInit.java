package io.github.bossmania.NetherandEndMaps.core.init;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

	//create the registry for items
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NetherandEndMaps.MOD_ID);
	
	//register an example item and add it into the misc group
	public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("nether_map", () -> 
	new Item(new Item.Properties().group(ItemGroup.MISC)));
}
