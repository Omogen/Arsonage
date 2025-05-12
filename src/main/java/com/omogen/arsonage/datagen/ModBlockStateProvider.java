package com.omogen.arsonage.datagen;

import java.util.Optional;
import com.omogen.arsonage.Arsonage;
import com.omogen.arsonage.blocks.CheeseBlock;
import com.omogen.arsonage.blocks.ModBlocks;
import com.omogen.arsonage.items.ModItems;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TextureSlot;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.data.PackOutput;

public class ModBlockStateProvider extends ModelProvider{

	public ModBlockStateProvider(PackOutput output) {
		super(output, Arsonage.MODID);
	}
	
    public void createCheeseBlock(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
    	blockModels.registerSimpleItemModel(ModBlocks.CHEESE_BLOCK.get(), cheeseModelTemplate
    			.create(
    					ModelLocationUtils.getModelLocation(
    							ModItems.CHEESE_BLOCK.get()),
    					TextureMapping.cube(
    							ModBlocks.CHEESE_BLOCK.get()),
    					itemModels.modelOutput)
    			);
        blockModels.blockStateOutput
            .accept(
                MultiVariantGenerator.dispatch(ModBlocks.CHEESE_BLOCK.get())
                    .with(
                        PropertyDispatch.initial(CheeseBlock.BITES)
                            .select(0, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.CHEESE_BLOCK.get(), "_full")))
                            .select(1, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.CHEESE_BLOCK.get(), "_bite_1")))
                            .select(2, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.CHEESE_BLOCK.get(), "_bite_2")))
                            .select(3, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.CHEESE_BLOCK.get(), "_bite_3")))
                            .select(4, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.CHEESE_BLOCK.get(), "_bite_4")))
                            .select(5, BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(ModBlocks.CHEESE_BLOCK.get(), "_full")))
                    )
            );
    }
	
	@Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // Model Generation
		createCheeseBlock(blockModels, itemModels);
		ItemModel.Unbaked BLAZE_AMULET = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(ModItems.BLAZE_AMULET.get()));
		itemModels.createFlatItemModel(ModItems.BLAZE_AMULET.get(), "", ModelTemplates.FLAT_ITEM);
		itemModels.itemModelOutput.
			accept(ModItems.BLAZE_AMULET.get(), BLAZE_AMULET);
		
    }
   	static ModelTemplate cheeseModelTemplate = new ModelTemplate(ModelTemplates.CUBE_ALL.model, Optional.empty(), TextureSlot.ALL);
}