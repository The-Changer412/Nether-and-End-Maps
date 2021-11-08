package io.github.bossmania.NetherandEndMaps.common.items;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import com.google.common.collect.Maps;
import com.mojang.brigadier.Command;
import com.mojang.realmsclient.dto.PlayerInfo;

import net.minecraft.block.Blocks;
import net.minecraft.client.world.DimensionRenderInfo.Nether;
import net.minecraft.command.impl.ExecuteCommand;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.DynamicLootEntry;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootContext.Builder;
import net.minecraft.loot.LootParameter;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.listener.IChunkStatusListener;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.ISpecialSpawner;
import net.minecraft.world.storage.IServerWorldInfo;
import net.minecraft.world.storage.SaveFormat;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.loot.functions.CopyName;
import net.minecraft.loot.functions.CopyNbt;
import net.minecraft.loot.functions.ExplorationMap;
import net.minecraft.loot.functions.SetContents;

public class ExplorerMap extends Item{

	public ExplorerMap(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		String[] dimensionList = playerIn.world.getDimensionKey().toString().split("/ ");
		String dimension = dimensionList[1].substring(0, dimensionList[1].length() -1);
		
//		try {
//			FileWriter log = new FileWriter("C:\\Users\\bossm\\Desktop\\log.txt");
//			log.write("\n"+playerIn.world.getDimensionKey().toString());
//			log.close();
//		} catch (IOException e) {
//			System.out.println("error");
//			e.printStackTrace();
//		}
		
//		playerIn.sendMessage(new TranslationTextComponent(dimension), playerIn.getGameProfile().getId());
		
		if (dimension.equals("minecraft:the_nether")==false) {
			playerIn.sendMessage(new TranslationTextComponent("You can't use the map in this dimension. This map can only be used in the nether."), playerIn.getGameProfile().getId());
		} else {

			LootTableManager ManageLootTables = new LootTableManager(null);
			LootTable table = ManageLootTables.getLootTableFromLocation(new ResourceLocation("nemaps:chests/generate_explorer_map.json"));
			MinecraftServer minecraftserver = worldIn.getServer();
			Map<LootParameter<?>, Object> lootParameters = Maps.newIdentityHashMap();
			Map<ResourceLocation, LootContext.IDynamicDropProvider> field_216026_c = Maps.newHashMap();

//			LootContext.Builder ctx = new LootContext.Builder().build(null);
//			LootContext LootTableContext = new LootContext(random, playerIn.getLuck(), worldIn, minecraftserver.getLootTableManager()::getLootTableFromLocation, minecraftserver.func_229736_aP_()::func_227517_a_, lootParameters, field_216026_c);
//			table.generate(LootTableContext);
//			table.generate(ctx);

			
			
//			ItemStack NetherFortressMap = new ItemStack(Items.FILLED_MAP);
//			CompoundNBT nbtTag = NetherFortressMap.getTag();
//			if (nbtTag == null) {
//				nbtTag = new CompoundNBT();
//		        NetherFortressMap.setTag(nbtTag);
//			}
//			nbtTag.putString("x", "0");
//			playerIn.inventory.addItemStackToInventory(NetherFortressMap);
			
		}
		
		return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
	}
}
