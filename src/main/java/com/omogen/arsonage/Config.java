package com.omogen.arsonage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = Arsonage.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue CHEESE_BLOCK_CRAFTABLE = BUILDER
            .comment("Should Cheese Block be craftable? Block will still exist but will not be craftable.")
            .define("CheeseBlockCraftable", true);

    // a list of strings that are treated as resource locations for entities
    @SuppressWarnings("deprecation")
	private static final ModConfigSpec.ConfigValue<List<? extends String>> SCORCH_EXCLUSION_LIST = BUILDER
            .comment("Mobs unaffected by Scorch Enchantment's AOE in format 'modid:entity_name' ie. 'minecraft:zombie'. Server will have priority.")
            .defineListAllowEmpty("ScorchExclusionList", List.of("minecraft:villager", "minecraft:wandering_trader", "minecraft:iron_golem", "minecraft:wolf", "minecraft:cat", "minecraft:allay"), Config::validateEntityName);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean cheeseBlockCraftable;
    public static Set<?> scorchExclusionEntities;

    private static boolean validateEntityName(final Object obj)
    {
        return obj instanceof String entityName && BuiltInRegistries.ENTITY_TYPE.containsKey(ResourceLocation.parse(entityName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        cheeseBlockCraftable = CHEESE_BLOCK_CRAFTABLE.get();

        // Converts the list of strings into a set of entities.
        scorchExclusionEntities = SCORCH_EXCLUSION_LIST.get().stream()
                .map(entityName -> BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.parse(entityName)))
                .collect(Collectors.toSet());
    }
}
