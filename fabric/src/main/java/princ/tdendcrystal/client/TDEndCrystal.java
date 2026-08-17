package princ.tdendcrystal.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.object.crystal.EndCrystalModel;

import static princ.tdendcrystal.client.Constants.ModelLayers.END_CRYSTAL;

public class TDEndCrystal implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        this.registerModelLayers();
    }

    public void registerModelLayers() {
        EntityModelLayerRegistry.registerModelLayer(END_CRYSTAL, EndCrystalModel::createBodyLayer);
    }
}
