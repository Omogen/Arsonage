package com.omogen.arsonage.enchantments;

import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import static com.omogen.arsonage.Arsonage.MODID;
import static com.omogen.arsonage.Config.scorchExclusionEntities;
public class ScorchEnchantment {
	public static final ResourceKey<Enchantment> SCORCH = ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath(MODID, "scorch"));
	
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
        entities = entities.stream()
                .filter(entity -> !scorchExclusionEntities.contains(entity.getType()))
                .collect(Collectors.toList());
        return entities;
	}
}