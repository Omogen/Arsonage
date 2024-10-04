package com.omogen.arsonage.utilities;

import java.util.function.Supplier;

import com.mojang.serialization.MapCodec;
import com.omogen.arsonage.Arsonage;
import com.omogen.arsonage.Config;

import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ConfigItemDisabledCondition implements ICondition
{
	// Thanks vectorwing.
	public static final MapCodec<ConfigItemDisabledCondition> CODEC = MapCodec.unit(new ConfigItemDisabledCondition());

	public ConfigItemDisabledCondition() {
	}

	@Override
	public boolean test(IContext context) {
		return Config.cheeseBlockCraftable;
	}

	@Override
	public MapCodec<? extends ICondition> codec() {
		return CODEC;
	}
	
	public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(NeoForgeRegistries.CONDITION_SERIALIZERS, Arsonage.MODID);

	public static final Supplier<MapCodec<? extends ICondition>> CHEESE_BLOCK_ENABLED = CONDITION_CODECS.register("cheese_block_enabled", () -> ConfigItemDisabledCondition.CODEC);
}