package princ.tdendcrystal.client;

import net.minecraft.client.model.object.crystal.EndCrystalModel;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static princ.tdendcrystal.client.Constants.ModelLayers.END_CRYSTAL;

@Mod(value = Constants.NAMESPACE, dist = Dist.CLIENT)
public class NeoForgeCMI {

    public NeoForgeCMI(IEventBus eventBus) {
        TDEndCrystal.init();
        eventBus.addListener(NeoForgeCMI::onRegisterLayerDefinition);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinition(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(END_CRYSTAL, EndCrystalModel::createBodyLayer);
    }
}