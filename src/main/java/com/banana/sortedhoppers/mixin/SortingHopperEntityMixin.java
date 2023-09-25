package com.banana.sortedhoppers.mixin;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;

@Mixin(HopperBlockEntity.class)
@Implements(@Interface(iface = Inventory.class, prefix = "inv$"))
public class SortingHopperEntityMixin {

    private static final BooleanProperty SORTED = BooleanProperty.of("sorted");
    
    public void inv$onOpen(PlayerEntity player) {
        player.sendMessage(Text.literal("Sorted!"));
    }

    @Shadow
    private int transferCooldown;
}