package com.cumulusmc.unbound.init;

import com.cumulusmc.unbound.Unbound;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class UnboundItems {

    public static final Item LUMIMANCERS_TOME = register(new FabricItemSettings(), "lumimancers_tome");

    public static final Item CELESTITE = register("celestite");

    public static void onInitialize() {}

    public static Item register(String name) {
        return register(new FabricItemSettings(), name);
    }

    public static Item register(FabricItemSettings s, String name) {
        return register(new Item(s), name);
    }

    public static <I extends Item> I register(I i, String name) {
        return Registry.register(Registry.ITEM, Unbound.id(name), i);
    }
}
