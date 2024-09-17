package com.omogen.arsonage.enchantments;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
public class ScorchEnchantment {

	public static List<Entity> getEntitiesAroundPlayer(Player pPlayer) {
        Level level = pPlayer.level();
        double x = pPlayer.getX();
        double y = pPlayer.getY();
        double z = pPlayer.getZ();
        ItemStack pItemStack = pPlayer.getMainHandItem();
        Holder<Enchantment> hasScorch = (Holder<Enchantment>) Enchantments.SHARPNESS; //FIXME
        int enchantmentLevel = pItemStack.getEnchantmentLevel(hasScorch);
        double radius = 1.0 * enchantmentLevel; // 2x2 block area means 1 block radius around the player\
        AABB boundingBox = new AABB(
                x - radius, y - radius, z - radius,
                x + radius, y + radius, z + radius
        );
        return level.getEntitiesOfClass(Entity.class, boundingBox);
    }
 
}