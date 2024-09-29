package com.omogen.arsonage.utilities;

import com.omogen.arsonage.Arsonage;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModTags {
	public static final TagKey<Enchantment> HANDHELD_FIRE_ENCHANTMENTS = TagKey.create(
	        Registries.ENCHANTMENT,
	        ResourceLocation.fromNamespaceAndPath(Arsonage.MODID, "handheld_fire_enchantments")
	);
}