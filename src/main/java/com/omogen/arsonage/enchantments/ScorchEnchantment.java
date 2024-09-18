package com.omogen.arsonage.enchantments;

import java.util.List;
import java.util.Optional;

import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import static com.omogen.arsonage.Arsonage.MODID;
public class ScorchEnchantment {

	public static List<Entity> getEntitiesAroundAttacker(Entity attacker, int enchantmentLevel) {
        Level level = attacker.level();
        double x = attacker.getX();
        double y = attacker.getY();
        double z = attacker.getZ();
        double radius = 3.0 * enchantmentLevel;
        AABB boundingBox = new AABB(
                x - radius, y - radius, z - radius,
                x + radius, y + radius, z + radius
        );
        List<Entity> entities = level.getEntitiesOfClass(Entity.class, boundingBox, entity -> entity != attacker && entity instanceof LivingEntity);
        return entities;
	}
}