package net.leo.herotech.item.custom;

import net.leo.herotech.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class ScannerItem extends Item {
    public ScannerItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide) {
            Player player = pContext.getPlayer();
            BlockPos pos = pContext.getClickedPos();
            boolean foundValuableBlock = false;

            for (int i = 0; i <= pContext.getClickedPos().getY() + 64; ++i) {
                Block below = pContext.getLevel().getBlockState(pos.below(i)).getBlock();
                if (isValuable(below)) {
                    foundValuableBlock = true;
                    break;
                }
            }


            sendFoundMessage(player, foundValuableBlock);

        }
        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(pContext.getHand()));

        return InteractionResult.SUCCESS;
    }

    private void sendFoundMessage(Player player, boolean found){
        player.sendMessage(new TranslatableComponent("item.herotech.scanner.found." + found),
                player.getUUID());
    }

    private boolean isValuable(Block block){
        return block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE ||
                block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE ||
                block == ModBlocks.COBALT_ORE.get() || block == ModBlocks.DEEPSLATE_COBALT_ORE.get();
    }
}
