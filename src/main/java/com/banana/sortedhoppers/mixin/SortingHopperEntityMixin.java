package com.banana.sortedhoppers.mixin;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

@Mixin(HopperBlockEntity.class)
@Implements(@Interface(iface = Inventory.class, prefix = "inv$"))
public class SortingHopperEntityMixin {

    public boolean sorted;

    @Inject(method = "readNbt", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    public void readNbt(NbtCompound nbt, CallbackInfo ci) {
        this.sorted = nbt.getBoolean("sorted");
    }

    @Inject(method = "writeNbt", at = @At(value = "TAIL"), locals = LocalCapture.CAPTURE_FAILHARD)
    protected void writeNbt(NbtCompound nbt, CallbackInfo ci) {
        nbt.putBoolean("sorted", this.sorted);
    }

    
    public void inv$onOpen(PlayerEntity player) {
        if (this.sorted)
            player.sendMessage(Text.literal("Sorted!"));
    }

    @Shadow
    private int transferCooldown;
}