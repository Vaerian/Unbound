package com.cumulusmc.unbound.init;

import com.cumulusmc.unbound.Unbound;
import com.cumulusmc.unbound.block.EphemeralGlass;
import com.cumulusmc.unbound.block.SoulGlass;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class UnboundBlocks {

    public static final Block SOUL_GLASS = register(new SoulGlass(), "soul_glass", new FabricItemSettings());
    public static final Block EPHEMERAL_GLASS = register(new EphemeralGlass(), "ephemeral_glass", new FabricItemSettings());

    public static void onInitialize() {}

    public static <B extends Block> B register(B b, String name, FabricItemSettings s) {
        Registry.register(Registry.BLOCK, Unbound.id(name), b);
        Registry.register(Registry.ITEM, Unbound.id(name), new BlockItem(b, s));
        return b;
    }

    public static Boolean never(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return false;
    }

    public static boolean never(BlockState state, BlockView blockView, BlockPos blockPos) {
        return false;
    }
}
