package com.banana.sortedhoppers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

@Mixin(value = HopperBlock.class, remap = false)
public class HopperBlockMixin {

    private static final BooleanProperty SORTED = BooleanProperty.of("sorted");

    @ModifyArg(method = "*", at = @At(value = "INVOKE", target = "setDefaultState(Lnet/minecraft/block/BlockState;)V"), index = 0)
    private BlockState setDefaultBlockStateModifyArg(BlockState state) {
        state = state.with(SORTED, false);
        return state;
    }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(SORTED);
    }

}
