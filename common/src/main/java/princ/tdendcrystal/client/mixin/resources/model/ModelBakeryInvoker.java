package princ.tdendcrystal.client.mixin.resources.model;

import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ModelBakery.class)
public interface ModelBakeryInvoker {

    @Invoker("loadSpecialItemModelAndDependencies")
    void tdEndCrystal$loadSpecialItemModelAndDependencies(ModelResourceLocation modelResourceLocation);
}
