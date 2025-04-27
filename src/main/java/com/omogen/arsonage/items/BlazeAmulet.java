package com.omogen.arsonage.items;

import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;
import com.omogen.arsonage.utilities.ModTimer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class BlazeAmulet extends Item{

	public static ItemStack BlazeAmuletItemStack = new ItemStack(ModItems.BLAZE_AMULET.get());
	
	public BlazeAmulet(Properties pProperties) {
		super(pProperties);
	}

	@SubscribeEvent
	public static void blazeAmuletEvent(LivingIncomingDamageEvent event) {
		LivingEntity damageReceiver = event.getEntity();
		if (damageReceiver instanceof Player pPlayer && damageReceiver.level() instanceof ServerLevel) {
			Inventory pInventory = pPlayer.getInventory();
			if ((event.getSource().is(DamageTypes.ON_FIRE) || event.getSource().is(DamageTypes.IN_FIRE) || event.getSource().is(DamageTypes.LAVA) || event.getSource().is(DamageTypes.HOT_FLOOR))
					&& pInventory.contains(BlazeAmuletItemStack)) {
				if (!(pPlayer.getCooldowns().isOnCooldown(ModItems.BLAZE_AMULET.get().getDefaultInstance())) && !(damageReceiver.hasEffect(MobEffects.FIRE_RESISTANCE))) {
					damageReceiver.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600));
					pPlayer.getCooldowns().addCooldown(ModItems.BLAZE_AMULET.get().getDefaultInstance(), 2400);
					event.setAmount(0);
					pPlayer.level().playSound(null, pPlayer.blockPosition(), SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 1.5F);
					pPlayer.level().playSound(null, pPlayer.blockPosition(), SoundEvents.ALLAY_DEATH, SoundSource.PLAYERS, 1.0F, 1.5F);
					ModTimer.scheduleTask(600, () -> pPlayer.clearFire());
					ModTimer.scheduleTask(2400, () -> pPlayer.level().playSound(null, pPlayer.blockPosition(), SoundEvents.GENERIC_EXTINGUISH_FIRE, SoundSource.PLAYERS, 1.0F, 2.0F));
				}
			}
		}
	}
}