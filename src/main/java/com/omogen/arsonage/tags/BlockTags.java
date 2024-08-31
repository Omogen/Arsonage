package com.omogen.arsonage.tags;

import java.util.concurrent.CompletableFuture;

import com.omogen.arsonage.blocks.ModBlocks;

import static com.omogen.arsonage.Arsonage.MODID;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockTags extends BlockTagsProvider {
    // Get parameters from GatherDataEvent.
    public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    // Add your tag entries here.
    @Override
    protected void addTags(HolderLookup.Provider lookupProvider) {
    }
}