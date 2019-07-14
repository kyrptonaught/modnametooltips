package net.kyrptonaught.modnametooltip;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import java.util.HashMap;


public class ModNameToolTipMod implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "modnameitemtool";
	public static HashMap<String,String> modCache = new HashMap<>();
	@Override
	public void onInitialize() {
	}

	@Override
	public void onInitializeClient() {
	}
}
