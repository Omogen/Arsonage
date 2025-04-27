package com.omogen.arsonage.components;

import java.util.function.UnaryOperator;

import com.mojang.serialization.Codec;
import com.omogen.arsonage.Arsonage;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
	public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
			DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Arsonage.MODID);
	
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> ARTEFACT_POWER =
            register("artefact_power", builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT));

	private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
		return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
	}
	
	public static void register(IEventBus eventBus) {
		DATA_COMPONENT_TYPES.register(eventBus);
	}
}