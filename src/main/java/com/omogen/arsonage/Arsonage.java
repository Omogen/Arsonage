package com.omogen.arsonage;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;
import com.omogen.arsonage.blocks.ModBlocks;
import com.omogen.arsonage.enchantments.ScorchEvent;
import com.omogen.arsonage.items.BlazeAmulet;
import com.omogen.arsonage.items.ModItems;
import com.omogen.arsonage.utilities.ConfigItemDisabledCondition;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Arsonage.MODID)
public class Arsonage
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "arsonage";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Arsonage(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // MOD BLOCKS REGISTRY
        ModBlocks.BLOCKS.register(modEventBus);
        // MOD ITEMS REGISTRY
        ModItems.ITEMS.register(modEventBus);
        // CREATIVE MODE TAB REGISTRY
        CREATIVE_MODE_TABS.register(modEventBus);
        // DATA LOADER CONDITIONS REGISTRY
        ConfigItemDisabledCondition.CONDITION_CODECS.register(modEventBus);
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // CONFIG REGISTRY
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        NeoForge.EVENT_BUS.addListener(ScorchEvent::onScorchAttack);
        NeoForge.EVENT_BUS.addListener(BlazeAmulet::blazeAmuletEvent);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    	
    }
    

    // ADD CREATIVEMENU ITEMS
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(ModItems.CHEESE_BLOCK);
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES)
            event.accept(ModItems.BLAZE_AMULET.get());
    }
    
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("Arsonage is installed.");
        }
    }
}
