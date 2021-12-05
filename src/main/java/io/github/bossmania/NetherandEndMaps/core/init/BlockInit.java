package io.github.bossmania.NetherandEndMaps.core.init;

import io.github.bossmania.NetherandEndMaps.NetherandEndMaps;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {
	//create the registry for blocks
	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, NetherandEndMaps.MOD_ID);
	
	//create the new cartographies table
	public static final RegistryObject<Block> NETHER_CARTOGRPAHY_TABLE = BLOCKS.register("nether_cartography_table", 
			() -> new Block(Properties.from(Blocks.CARTOGRAPHY_TABLE)));
	
	public static final RegistryObject<Block> END_CARTOGRPAHY_TABLE = BLOCKS.register("end_cartography_table", 
			() -> new Block(Properties.from(Blocks.CARTOGRAPHY_TABLE)));
}
