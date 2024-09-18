package com.omogen.arsonage.utilities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class ArsonageLootModifier extends LootModifier{
	// See below for how the codec works.
	public static final MapCodec<ArsonageLootModifier> CODEC = RecordCodecBuilder.mapCodec(inst -> 
    // LootModifier#codecStart adds the conditions field.
    LootModifier.codecStart(inst).and(inst.group(
            Codec.STRING.fieldOf("field1").forGetter(e -> e.field1),
            Codec.INT.fieldOf("field2").forGetter(e -> e.field2),
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("field3").forGetter(e -> e.field3)
    )).apply(inst, ArsonageLootModifier::new)
);
    // Our extra properties.
    private final String field1;
    private final int field2;
    private final Item field3;
    
    // First constructor parameter is the list of conditions. The rest is our extra properties.
    public ArsonageLootModifier(LootItemCondition[] conditions, String field1, int field2, Item field3) {
        super(conditions);
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }
    
    // Return our codec here.
    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
    
    // This is where the magic happens. Use your extra properties here if needed.
    // Parameters are the existing loot, and the loot context.
    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        // Add your items to generatedLoot here.
        return generatedLoot;
    }
}
