package com.cumulusmc.unbound.client;

import com.cumulusmc.unbound.init.UnboundBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class UnboundClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(UnboundBlocks.SOUL_GLASS, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(UnboundBlocks.EPHEMERAL_GLASS, RenderLayer.getTranslucent());
    }
}
