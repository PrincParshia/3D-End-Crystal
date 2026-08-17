package princ.tdendcrystal.client.renderer.special;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.object.crystal.EndCrystalModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.EndCrystalRenderState;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3fc;

import java.util.function.Consumer;

import static princ.tdendcrystal.client.Constants.ModelLayers.END_CRYSTAL;

public class EndCrystalSpecialRenderer implements SpecialModelRenderer<EndCrystalRenderState> {
    private static final Identifier TEXTURE = Identifier.withDefaultNamespace("textures/entity/end_crystal/end_crystal.png");
    private static final float SIN_45 = (float) Math.sin(Math.PI / 4);
    private final EndCrystalModel model;

    public EndCrystalSpecialRenderer(EndCrystalModel model) {
        this.model = model;
    }

    @Override
    public EndCrystalRenderState extractArgument(ItemStack stack) {
        EndCrystalRenderState state = new EndCrystalRenderState();
        state.showsBottom = false;
        state.beamOffset = null;

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if (player != null) {
            state.ageInTicks = player.tickCount + minecraft.getDeltaTracker().getGameTimeDeltaPartialTick(false);
        }

        return state;
    }

    @Override
    public void submit(EndCrystalRenderState state, ItemDisplayContext context, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, int overlayCoords, boolean hasFoil, final int outlineColor) {
        poseStack.pushPose();
        this.setupAnim(state);
        poseStack.translate(0.5, -1, 0.5);
        submitNodeCollector.submitModelPart(this.model.root(), poseStack, this.model.renderType(TEXTURE), lightCoords, overlayCoords, null);
        poseStack.popPose();
    }

    public void setupAnim(EndCrystalRenderState state) {
        this.model.resetPose();
        this.model.base.visible = false;
        float animationSpeed = state.ageInTicks * 3.0F;
        this.model.outerGlass.rotateBy(Axis.YP.rotationDegrees(animationSpeed).rotateAxis(((float) Math.PI / 3F), SIN_45, 0.0F, SIN_45));
        this.model.innerGlass.rotateBy((new Quaternionf()).setAngleAxis(((float) Math.PI / 3F), SIN_45, 0.0F, SIN_45).rotateY(animationSpeed * ((float) Math.PI / 180F)));
        this.model.cube.rotateBy((new Quaternionf()).setAngleAxis(((float) Math.PI / 3F), SIN_45, 0.0F, SIN_45).rotateY(animationSpeed * ((float) Math.PI / 180F)));
    }

    @Override
    public void getExtents(Consumer<Vector3fc> output) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, output);
    }

    public record Unbaked() implements SpecialModelRenderer.Unbaked {
        public static final MapCodec<Unbaked> MAP_CODEC = MapCodec.unit(new Unbaked());

        @Override
        public EndCrystalSpecialRenderer bake(SpecialModelRenderer.BakingContext context) {
            return new EndCrystalSpecialRenderer(new EndCrystalModel(context.entityModelSet().bakeLayer(END_CRYSTAL)));
        }

        @Override
        public MapCodec<Unbaked> type() {
            return MAP_CODEC;
        }
    }
}
