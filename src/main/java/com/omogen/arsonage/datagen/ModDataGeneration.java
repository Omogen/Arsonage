package com.omogen.arsonage.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;


@EventBusSubscriber(modid = "arsonage", bus = EventBusSubscriber.Bus.MOD)
public class ModDataGeneration {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator dataGenerator = event.getGenerator();
		PackOutput packOutput = dataGenerator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		
		dataGenerator.addProvider(true, new LootTableProvider(packOutput, Collections.emptySet(), 
				List.of(new LootTableProvider.SubProviderEntry(ModBlockLootTableProvider::new, LootContextParamSets.BLOCK)), lookupProvider));
		dataGenerator.addProvider(true, new ModRecipeProvider.RecipeRunner(packOutput, lookupProvider));
		dataGenerator.addProvider(true, new ModBlockStateProvider(packOutput));
	}
}
