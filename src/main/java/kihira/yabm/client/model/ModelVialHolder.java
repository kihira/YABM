package kihira.yabm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelVialHolder extends ModelBase {

    ModelRenderer backPlate;
    ModelRenderer frontPlate;
    ModelRenderer side1;
    ModelRenderer side2;
    ModelRenderer base;

    public ModelVialHolder() {
        textureWidth = 64;
        textureHeight = 32;

        backPlate = new ModelRenderer(this, 0, 0);
        backPlate.addBox(0F, 0F, 0F, 8, 4, 1);
        backPlate.setRotationPoint(-4F, 0F, 0F);
        backPlate.mirror = true;
        setRotation(backPlate, 0F, 0F, 0F);
        frontPlate = new ModelRenderer(this, 0, 5);
        frontPlate.addBox(0F, 0F, 0F, 8, 2, 1);
        frontPlate.setRotationPoint(-4F, 2F, -2F);
        frontPlate.mirror = true;
        setRotation(frontPlate, 0F, 0F, 0F);
        side1 = new ModelRenderer(this, 18, 0);
        side1.addBox(0F, 0F, 0F, 1, 1, 1);
        side1.setRotationPoint(3F, 3F, -1F);
        side1.mirror = true;
        setRotation(side1, 0F, 0F, 0F);
        side2 = new ModelRenderer(this, 18, 0);
        side2.addBox(0F, 0F, 0F, 1, 1, 1);
        side2.setRotationPoint(-4F, 3F, -1F);
        side2.mirror = true;
        setRotation(side2, 0F, 0F, 0F);
        base = new ModelRenderer(this, 18, 2);
        base.addBox(0F, 0F, 0F, 8, 0, 3);
        base.setRotationPoint(-4F, 4F, -2F);
        base.mirror = true;
        setRotation(base, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5);
        backPlate.render(f5);
        frontPlate.render(f5);
        side1.render(f5);
        side2.render(f5);
        base.render(f5);
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
