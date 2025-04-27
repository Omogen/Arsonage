package com.omogen.arsonage.items;

import static com.omogen.arsonage.Arsonage.MODID;
import static net.minecraft.world.item.Rarity.RARE;

import com.omogen.arsonage.blocks.ModBlocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
		public static final DeferredItem<BlockItem> CHEESE_BLOCK = ITEMS.registerSimpleBlockItem(ModBlocks.CHEESE_BLOCK);
		public static final DeferredItem<Item> BLAZE_AMULET = ITEMS.registerItem(
		        "blaze_amulet",
		        BlazeAmulet::new,
		        new Item.Properties()
		        	.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(MODID, "blaze_amulet")))
		        	.fireResistant()
		        	.rarity(RARE)
		        	.stacksTo(1)
		);
}