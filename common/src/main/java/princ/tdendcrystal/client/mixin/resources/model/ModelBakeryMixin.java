package princ.tdendcrystal.client.mixin.resources.model;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.util.profiling.ProfilerFiller;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static princ.tdendcrystal.client.Constants.ModelResources.END_CRYSTAL;

@Mixin(ModelBakery.class)
public class ModelBakeryMixin {

    @WrapOperation(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V"
            )
    )
    void tdEndCrystal$loadEndCrystal(ProfilerFiller profilerFiller, String string, Operation<Void> original) {
        original.call(profilerFiller, string);
        if (string.equals("special")) {
            ((ModelBakeryInvoker) this).tdEndCrystal$loadSpecialItemModelAndDependencies(END_CRYSTAL);
        }
    }
}
