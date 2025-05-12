package com.omogen.arsonage.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;
import net.neoforged.neoforge.common.conditions.NotCondition;
import java.util.concurrent.CompletableFuture;

import com.omogen.arsonage.items.ModItems;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.core.registries.Registries;

public class ModRecipeProvider extends RecipeProvider {

	public ModRecipeProvider(Provider registries, RecipeOutput output) {
		super(registries, output);
	}

	public final void buildRecipes() {
	ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, ModItems.BLAZE_AMULET.get())
    .pattern("GDG")
    .pattern("OBO")
    .pattern("GDG")

    .define('G', Items.GOLD_INGOT)
    .define('D', Items.DIAMOND)
    .define('O', Items.OBSIDIAN)
    .define('B', Items.BLAZE_POWDER)

    .unlockedBy("has_gold_ingot", this.has(Items.GOLD_INGOT))
    .unlockedBy("has_diamond", this.has(Items.DIAMOND))
    .unlockedBy("has_obsidian", this.has(Items.OBSIDIAN))

    .save(this.output);
	
/*	ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModItems.CHEESE_BLOCK.get())
    .pattern("CC")
    .pattern("CC")

    .define('C', croptopiaCheese)

    .unlockedBy("has_croptopia_cheese", this.has(croptopiaCheese))

    .save(this.output
    		.withConditions(
    			new ModLoadedCondition("croptopia")));
*/
	ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.BUILDING_BLOCKS, ModItems.CHEESE_BLOCK.get())
	.requires(Items.MILK_BUCKET)
	.unlockedBy("has_milk_bucket", this.has(Items.MILK_BUCKET))
	.save(this.output
			.withConditions(
				new NotCondition(
						new ModLoadedCondition("croptopia"))));
	/*
	ShapelessRecipeBuilder.shapeless(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.FOOD, croptopiaCheese, 4)
	.requires(ModItems.CHEESE_BLOCK.get())
	.unlockedBy("has_cheese_block", this.has(ModItems.CHEESE_BLOCK.get()))
	.save(this.output
			.withConditions(
					new ModLoadedCondition("croptopia")));	*/
	}
	
	public static class RecipeRunner extends RecipeProvider.Runner {
        public RecipeRunner(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(output, lookupProvider);
        }

		@Override
		public String getName() {
			return "Arsonage Recipes";
		}

		@Override
		protected RecipeProvider createRecipeProvider(Provider registries, RecipeOutput output) {
			return new ModRecipeProvider(registries, output);
		}
		
    }
}
