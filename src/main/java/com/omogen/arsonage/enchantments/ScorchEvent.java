package com.omogen.arsonage.enchantments;

import java.util.List;

import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import static com.omogen.arsonage.Arsonage.MODID;

public class ScorchEvent extends ScorchEnchantment {
	private static final ThreadLocal<Boolean> isApplyingDamage = ThreadLocal.withInitial(() -> false);

	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void onScorchAttack(LivingIncomingDamageEvent event) {
		if (isApplyingDamage.get()) {
			return;
		}
		Entity attacker = event.getSource().getEntity();
		if (attacker instanceof LivingEntity living) {
			Level level = attacker.level();
			if (level instanceof ServerLevel serverLevel) {
				Holder<Enchantment> hasScorch = level.holderOrThrow(Enchantments.SHARPNESS);
				ItemStack weapon = attacker.getWeaponItem();
				int enchantmentLevel = weapon.getEnchantmentLevel(hasScorch);
				if (enchantmentLevel > 0) {
					List<Entity> entities = ScorchEnchantment.getEntitiesAroundAttacker(attacker, enchantmentLevel);
					for (Entity entity : entities) {
						entity.igniteForSeconds(3 + (2 * enchantmentLevel));
						isApplyingDamage.set(true);
						DamageSource attackerSource = event.getSource();
						entity.hurt(attackerSource, 0);
						isApplyingDamage.set(false);
					}
				}
			}
		}
	}
}
