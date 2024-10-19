package com.omogen.arsonage.items;

import static com.omogen.arsonage.Arsonage.MODID;

import java.util.function.Supplier;

import com.omogen.arsonage.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import static net.minecraft.world.item.Rarity.RARE;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
		public static final DeferredItem<BlockItem> CHEESE_BLOCK = ITEMS.registerSimpleBlockItem(ModBlocks.CHEESE_BLOCK);
		public static final Supplier<Item> BLAZE_AMULET = ITEMS.registerItem(
		        "blaze_amulet",
		        BlazeAmulet::new,
		        new Item.Properties()
		        	.fireResistant()
		        	.rarity(RARE)
		        	.setNoRepair()
		        	.stacksTo(1)
		);
}