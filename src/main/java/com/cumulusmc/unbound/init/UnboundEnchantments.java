package com.cumulusmc.unbound.init;

import com.cumulusmc.unbound.Unbound;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.registry.Registry;

public class UnboundEnchantments {

    /*public static final Enchantment REAPING*/

    public static void onInitialize() {}

    public static <E extends Enchantment> E register(E e, String s) {
        return Registry.register(Registry.ENCHANTMENT, Unbound.id(s), e);
    }
}
