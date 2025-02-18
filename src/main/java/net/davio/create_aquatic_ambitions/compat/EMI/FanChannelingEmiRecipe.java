package net.davio.create_aquatic_ambitions.compat.EMI;

import com.simibubi.create.compat.emi.CreateEmiAnimations;
import com.simibubi.create.compat.emi.recipes.fan.FanEmiRecipe;
import com.simibubi.create.foundation.gui.element.GuiGameElement;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.davio.create_aquatic_ambitions.entry.CCAPartials;
import net.davio.create_aquatic_ambitions.kinetics.fan.processing.ChannelingRecipe;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.util.Mth;

public class FanChannelingEmiRecipe extends FanEmiRecipe.MultiOutput<ChannelingRecipe> {

    public FanChannelingEmiRecipe(ChannelingRecipe recipe) {
        super(CCAEmiPlugin.CHANNELING, recipe);
    }

    public static float getConduitHeight() {
        return (0.2f * (Mth.sin(((AnimationTickHolder.getRenderTime() * 4f) % 360) * (float) Math.PI / 180)));
    }

    public static float getConduitAngle(boolean xAxis) {
        if (xAxis) {
            return 30 * (Mth.sin(((AnimationTickHolder.getRenderTime() * 2f) % 360) * (float) Math.PI / 180));
        } else {
            return (AnimationTickHolder.getRenderTime() * 2f) % 360;
        }
    }

    @Override
    protected void renderAttachedBlock(GuiGraphics graphics) {

        GuiGameElement.of(CCAPartials.CONDUIT_CAGE)
                .scale(SCALE)
                .atLocal(0, getConduitHeight(), 2)
                .rotateBlock(getConduitAngle(true), getConduitAngle(false), 0)
                .lighting(CreateEmiAnimations.DEFAULT_LIGHTING)
                .render(graphics);
        GuiGameElement.of(CCAPartials.CONDUIT_EYE)
                .scale(SCALE)
                .atLocal(0, getConduitHeight(), 2)
                .lighting(CreateEmiAnimations.DEFAULT_LIGHTING)
                .rotateBlock(0, -22.5, 0)
                .render(graphics);
        GuiGameElement.of(CCAPartials.CONDUIT_WIND)
                .scale(SCALE)
                .atLocal(0, 0, 2)
                .lighting(CreateEmiAnimations.DEFAULT_LIGHTING)
                .render(graphics);

    }
}
