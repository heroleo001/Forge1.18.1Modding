package net.leo.herotech.blockentity;

import net.leo.herotech.HeroTech;
import net.leo.herotech.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, HeroTech.MOD_ID);




    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
