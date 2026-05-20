package princ.tdendcrystal.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.object.crystal.EndCrystalModel;

import static princ.tdendcrystal.client.Constants.ModelLayers.END_CRYSTAL;

public class FabricCMI implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        TDEndCrystal.init();
        this.registerModelLayers();
    }

    public void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(END_CRYSTAL, EndCrystalModel::createBodyLayer);
    }
}
