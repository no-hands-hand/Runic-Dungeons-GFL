package mrcomputerghost.runicdungeons.entity.render;

import mrcomputerghost.runicdungeons.RunicDungeons;
import mrcomputerghost.runicdungeons.entity.EntityDungeonGuardian;
import mrcomputerghost.runicdungeons.entity.model.ModelDungeonGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderDungeonGuardian extends RenderLiving<EntityDungeonGuardian> {

    private static final ResourceLocation TEXTURES = new ResourceLocation(RunicDungeons.MODID +":textures/entity/guardian.png");

    public RenderDungeonGuardian(RenderManager rendermanager) {
        super(rendermanager, new ModelDungeonGuardian(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityDungeonGuardian entity) {
        return TEXTURES;
    }

    @Override
    protected void applyRotations(EntityDungeonGuardian guardian, float p_77043_2_, float rotationYaw, float partialTicks) {
        super.applyRotations(guardian, p_77043_2_, rotationYaw, partialTicks);

        if ((double)guardian.limbSwingAmount >= 0.01D) {
            float f = 13.0F;
            float f1 = guardian.limbSwing - guardian.limbSwingAmount * (1.0F - partialTicks) + 6.0F;
            float f2 = (Math.abs(f1 % 13.0F - 6.5F) - 3.25F) / 3.25F;
            GlStateManager.rotate(6.5F * f2, 0.0F, 0.0F, 1.0F);
        }
    }

}