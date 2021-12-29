package net.leo.herotech.util;

import net.leo.herotech.HeroTech;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import org.lwjgl.system.CallbackI;

public class ModTags {
    public static void register(){
        Items.registerItemTags();
        Blocks.registerBlockTags();
    }

    public static class Items{
        public static final Tags.IOptionalNamedTag<Item> COBALT_INGOTS = forgeTag("ingots/cobalt");
        public static final Tags.IOptionalNamedTag<Item> COBALT_NUGGETS = forgeTag("nuggets/cobalt");


        public static Tags.IOptionalNamedTag<Item> tag(String name){
            return ItemTags.createOptional(new ResourceLocation(HeroTech.MOD_ID, name));
        }
        public static Tags.IOptionalNamedTag<Item> forgeTag(String name){
            return ItemTags.createOptional(new ResourceLocation("forge", name));
        }
        public static void registerItemTags(){}
    }

    public static class Blocks{
        public static final Tags.IOptionalNamedTag<Block> SCANNER_VALUABLES = tag("scanner_valuables");


        public static Tags.IOptionalNamedTag<Block> tag(String name){
            return BlockTags.createOptional(new ResourceLocation(HeroTech.MOD_ID, name));
        }
        public static Tags.IOptionalNamedTag<Block> forgeTag(String name){
            return BlockTags.createOptional(new ResourceLocation("forge", name));
        }
        public static void registerBlockTags(){}
    }
}
