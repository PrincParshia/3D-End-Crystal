package princ.tdendcrystal.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String NAMESPACE = "td_end_crystal";
	public static final String NAME = "3D End Crystal";
	public static final Logger LOG = LoggerFactory.getLogger(NAME);

	public static ResourceLocation withDefaultNamespace(String string) {
		return ResourceLocation.fromNamespaceAndPath(NAMESPACE, string);
	}

	public static class ModelLayers {
		public static final ModelLayerLocation END_CRYSTAL = new ModelLayerLocation(withDefaultNamespace("item/end_crystal"), "main");
	}

	public static class ModelResources {
		public static final ModelResourceLocation END_CRYSTAL = ModelResourceLocation.inventory(withDefaultNamespace("end_crystal"));
	}
}