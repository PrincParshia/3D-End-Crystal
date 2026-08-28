package princ.tdendcrystal.client.mixin.renderer;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import princ.tdendcrystal.client.model.EndCrystalModel;

import static princ.tdendcrystal.client.Constants.ModelLayers.END_CRYSTAL;

@Mixin(BlockEntityWithoutLevelRenderer.class)
public class BlockEntityWithoutLevelRendererMixin {

    @Shadow
    @Final
    private EntityModelSet entityModelSet;

    @Unique
    EndCrystalModel tdEndCrystal$endCrystalModel;

    @Inject(method = "onResourceManagerReload", at = @At("HEAD"))
    void tdEndCrystal$onResourceManagerReload(ResourceManager resourceManager, CallbackInfo callbackInfo) {
        this.tdEndCrystal$endCrystalModel = new EndCrystalModel(this.entityModelSet.bakeLayer(END_CRYSTAL));
    }

    @WrapOperation(
            method = "renderByItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    boolean tdEndCrystal$renderByItem(ItemStack itemStack, Item item, Operation<Boolean> original, ItemStack itemStackMethod, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        if (itemStack.is(Items.END_CRYSTAL)) {
            Minecraft minecraft = Minecraft.getInstance();
            float ageInTicks = minecraft.player != null ? minecraft.player.tickCount + minecraft.getTimer().getGameTimeDeltaPartialTick(false) : 0.0F;
            poseStack.pushPose();
            poseStack.translate(0.5F, -0.5F, 0.5F);
            poseStack.scale(0.5F, 0.5F, 0.5F);
            VertexConsumer vertexConsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.tdEndCrystal$endCrystalModel.renderType(EndCrystalModel.END_CRYSTAL_LOCATION), false, itemStack.hasFoil());
            this.tdEndCrystal$endCrystalModel.renderToBuffer(poseStack, vertexConsumer, packedLight, packedOverlay, ageInTicks);
            poseStack.popPose();
            return false;
        }
        return original.call(itemStack, item);
    }
}
