package com.cumulusmc.unbound;

import com.cumulusmc.unbound.init.UnboundBlocks;
import com.cumulusmc.unbound.init.UnboundItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Unbound implements ModInitializer {

	public static final String MODID = "unbound";

	@Override
	public void onInitialize() {
		UnboundBlocks.onInitialize();
		UnboundItems.onInitialize();
	}

	public static Identifier id(String s) {
		return new Identifier(MODID, s);
	}
}
