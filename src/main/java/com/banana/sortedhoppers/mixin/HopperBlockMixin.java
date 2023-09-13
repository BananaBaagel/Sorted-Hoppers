package com.banana.sortedhoppers.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.state.State;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;

@Mixin(HopperBlock.class)
public class HopperBlockMixin {

    public static final BooleanProperty SORTED = BooleanProperty.of("sorted");

    @Inject(method = "<clinit>", at = @At("TAIL"))
    public void HopperBlockInjected(AbstractBlock.Settings settings, CallbackInfo ci) {
        this.setDefaultState(this.getDefaultState().with(SORTED, true));
    }
    
    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(SORTED);
    }

    @Shadow
    private void setDefaultState(BlockState with) {
    }

    @Shadow
    private State<Block, BlockState> getDefaultState() {
        return null;
    }

}
