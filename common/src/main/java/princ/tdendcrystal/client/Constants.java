package princ.tdendcrystal.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Constants {
	public static final String NAMESPACE = "td_end_crystal";
	public static final String NAME = "3D End Crystal";
	public static final Logger LOG = LoggerFactory.getLogger(NAME);

	public static Identifier withDefaultNamespace(String string) {
		return Identifier.fromNamespaceAndPath(NAMESPACE, string);
	}

	public static class ModelLayers {
		public static final ModelLayerLocation END_CRYSTAL = new ModelLayerLocation(withDefaultNamespace("item/end_crystal"), "main");
	}
}