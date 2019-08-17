package net.kyrptonaught.modnametooltip.mixin;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.kyrptonaught.modnametooltip.ModNameToolTipMod;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemStack.class)
public class ItemMixin {

    @Shadow
    @Final
    private Item item;

    @Inject(at = @At("TAIL"), method = "getTooltip")
    @Environment(EnvType.CLIENT)
    private void MNTT$getTooltip(PlayerEntity playerEntity_1, TooltipContext tooltipContext_1, CallbackInfoReturnable callbackInfoReturnable) {
        List<Text> list = (List<Text>) callbackInfoReturnable.getReturnValue();
        String mod = Registry.ITEM.getId(item).getNamespace();
        if (!ModNameToolTipMod.modCache.containsKey(mod)) {
            ModNameToolTipMod.modCache.put(mod, StringUtils.capitalize(FabricLoader.getInstance().getModContainer(mod).map(ModContainer::getMetadata).map(ModMetadata::getName).orElse(mod)));
        }
        mod = ModNameToolTipMod.modCache.get(mod);
        list.add(new TranslatableText(mod).formatted(Formatting.BLUE, Formatting.ITALIC));
    }
}
