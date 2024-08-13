package com.omogen.arsonage.blocks;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.item.BlockItem;

import com.omogen.arsonage.Arsonage;
import static com.omogen.arsonage.Arsonage.MODID;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

public class ModBlocks{
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
	public static final DeferredBlock<Block> CHEESE_BLOCK = BLOCKS.register("cheese_block", () -> new CheeseBlock(BlockBehaviour.Properties.of()
																								.destroyTime(3f)
																								.explosionResistance(6f)
																								.sound(SoundType.HONEY_BLOCK)
																							));
																								
	}