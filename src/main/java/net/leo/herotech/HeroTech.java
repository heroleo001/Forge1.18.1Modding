package net.leo.herotech;

import net.leo.herotech.block.ModBlocks;
import net.leo.herotech.blockentity.ModBlockEntities;
import net.leo.herotech.blockentity.screen.ModMenuTypes;
import net.leo.herotech.item.ModItems;
import net.leo.herotech.util.ModTags;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HeroTech.MOD_ID)
public class HeroTech {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "herotech";

    public HeroTech() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addListener(this::setup);
        eventBus.addListener(this::clientSetup);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModTags.register();

        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void clientSetup(final FMLClientSetupEvent event){

    }
}