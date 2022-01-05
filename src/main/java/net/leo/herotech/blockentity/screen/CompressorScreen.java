package net.leo.herotech.blockentity.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.leo.herotech.HeroTech;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CompressorScreen extends AbstractContainerScreen<CompressorMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(HeroTech.MOD_ID, "textures/gui/compressor_gui.png");

    public CompressorScreen(CompressorMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTicks, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if (menu.isCrafting()) {
            this.blit(pPoseStack, x+ 82, y+ 38, 176, 0, menu.getScaledProgress(), 9);
        }
        if (menu.hasEnergy()) {
            this.blit(pPoseStack, x+ 84, y+ 58 + 13 - menu.getScaledEnergyProgress(), 176,
                    9 + 14 - menu.getScaledEnergyProgress(), 7, menu.getScaledEnergyProgress());
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
