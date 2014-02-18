package kihira.yabm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBackpack extends ModelBase {

    ModelRenderer backpackBase;
    ModelRenderer backpackTop;
    ModelRenderer backpackDetail1;
    ModelRenderer backpackDetail2;
    ModelRenderer backpackSidePocket1;
    ModelRenderer backpackSidePocket2;

    public ModelBackpack() {
        textureWidth = 64;
        textureHeight = 32;

        backpackBase = new ModelRenderer(this, 0, 0);
        backpackBase.addBox(0F, 0F, 0F, 4, 10, 8);
        backpackBase.setRotationPoint(0F, 0F, -4F);
        backpackBase.mirror = true;
        setRotation(backpackBase, 0F, 0F, 0F);
        backpackTop = new ModelRenderer(this, 24, 0);
        backpackTop.addBox(0F, 0F, 0F, 4, 1, 7);
        backpackTop.setRotationPoint(0F, -0.5F, -3.5F);
        backpackTop.mirror = true;
        setRotation(backpackTop, 0F, 0F, 0F);
        backpackDetail1 = new ModelRenderer(this, 46, 0);
        backpackDetail1.addBox(0F, 0F, 0F, 1, 9, 7);
        backpackDetail1.setRotationPoint(-0.5F, 0.5F, -3.5F);
        backpackDetail1.mirror = true;
        setRotation(backpackDetail1, 0F, 0F, 0F);
        backpackDetail2 = new ModelRenderer(this, 24, 8);
        backpackDetail2.addBox(0F, 0F, 0F, 1, 3, 5);
        backpackDetail2.setRotationPoint(-1.5F, 6F, -2.5F);
        backpackDetail2.mirror = true;
        setRotation(backpackDetail2, 0F, 0F, 0F);
        backpackSidePocket1 = new ModelRenderer(this, 36, 8);
        backpackSidePocket1.addBox(0F, 0F, 0F, 3, 4, 1);
        backpackSidePocket1.setRotationPoint(0.5F, 5F, 4F);
        backpackSidePocket1.mirror = true;
        setRotation(backpackSidePocket1, 0F, 0F, 0F);
        backpackSidePocket2 = new ModelRenderer(this, 36, 8);
        backpackSidePocket2.addBox(0F, 0F, 0F, 3, 4, 1);
        backpackSidePocket2.setRotationPoint(0.5F, 5F, -5F);
        backpackSidePocket2.mirror = true;
        setRotation(backpackSidePocket2, 0F, 0F, 0F);
        backpackSidePocket2.mirror = false;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        backpackBase.render(f5);
        backpackTop.render(f5);
        backpackDetail1.render(f5);
        backpackDetail2.render(f5);
        backpackSidePocket1.render(f5);
        backpackSidePocket2.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
    }
}
