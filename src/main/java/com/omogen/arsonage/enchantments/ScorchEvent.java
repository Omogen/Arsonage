package com.omogen.arsonage.enchantments;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class ScorchEvent extends ScorchEnchantment {
	private static final ThreadLocal<Boolean> isApplyingDamage = ThreadLocal.withInitial(() -> false);

	@SubscribeEvent
	// Thanks to Vectorwing for his enchantment class which this is based off.
	public static void onScorchAttack(LivingIncomingDamageEvent event) {
		if (isApplyingDamage.get()) {
			return;
		}
		Entity attacker = event.getSource().getEntity();
		Entity attackedEntity = event.getEntity();
		if (attacker instanceof LivingEntity) {
			Level level = attacker.level();
			if (level instanceof ServerLevel serverLevel) {
				Holder<Enchantment> hasScorch = level.holderOrThrow(SCORCH);
				ItemStack weapon = attacker.getWeaponItem();
				int enchantmentLevel = weapon.getEnchantmentLevel(hasScorch);
				if (enchantmentLevel > 0) {
					attackedEntity.igniteForSeconds(3 + (2 * enchantmentLevel));
					List<Entity> entities = ScorchEnchantment.getEntitiesAroundAttacker(attacker, enchantmentLevel);
					for (Entity entity : entities) {
						entity.igniteForSeconds(3 + (2 * enchantmentLevel));
						isApplyingDamage.set(true);
						DamageSource attackerSource = event.getSource();
						entity.hurtServer(serverLevel, attackerSource, enchantmentLevel);
						isApplyingDamage.set(false);
					}
				}
			}
		}
	}
}