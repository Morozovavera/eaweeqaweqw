package sekelsta.horse_colors.renderer;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.model.*;
import sekelsta.horse_colors.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

@SideOnly(Side.CLIENT)
public class HorseGeneticModel extends ModelBase
{
    private final ModelRenderer head;
    private final ModelRenderer horn;
    private final ModelRenderer babyHorn;
    private final ModelRenderer upperMouth;
    private final ModelRenderer lowerMouth;
    private final ModelRenderer horseLeftEar;
    private final ModelRenderer horseRightEar;
    private final ModelRenderer muleLeftEar;
    private final ModelRenderer muleRightEar;
    private final ModelRenderer neck;
    private final ModelRenderer horseFaceRopes;
    private final ModelRenderer mane;
    private final ModelRenderer body;
    private final ModelRenderer tailBase;
    private final ModelRenderer tailMiddle;
    private final ModelRenderer tailTip;
    private final ModelRenderer tailThin;
    private final ModelRenderer tailTuft;
    private final ModelRenderer backLeftLeg;
    private final ModelRenderer backLeftShin;
    private final ModelRenderer backLeftHoof;
    private final ModelRenderer backRightLeg;
    private final ModelRenderer backRightShin;
    private final ModelRenderer backRightHoof;
    private final ModelRenderer frontLeftLeg;
    private final ModelRenderer frontLeftShin;
    private final ModelRenderer frontLeftHoof;
    private final ModelRenderer frontRightLeg;
    private final ModelRenderer frontRightShin;
    private final ModelRenderer frontRightHoof;
    private final ModelRenderer muleLeftChest;
    private final ModelRenderer muleRightChest;
    private final ModelRenderer horseLeftSaddleRope;
    private final ModelRenderer horseRightSaddleRope;
    private final ModelRenderer[] tackArray;
    private final ModelRenderer[] extraTackArray;
    private float ageScale;
    
    public HorseGeneticModel() {
        this(0.0f);
    }
    
    public HorseGeneticModel(final float scaleFactor) {
        this.ageScale = 0.5f;
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        (this.body = new ModelRenderer((ModelBase)this, 0, 34)).func_78790_a(-5.0f, -8.0f, -19.0f, 10, 10, 24, scaleFactor);
        this.body.func_78793_a(0.0f, 11.0f, 9.0f);
        (this.tailBase = new ModelRenderer((ModelBase)this, 44, 0)).func_78790_a(-1.0f, -1.0f, 0.0f, 2, 2, 3, scaleFactor);
        this.tailBase.func_78793_a(0.0f, -8.0f, 5.0f);
        this.tailBase.field_78795_f = -1.134464f;
        (this.tailMiddle = new ModelRenderer((ModelBase)this, 38, 7)).func_78790_a(-1.5f, -2.0f, 3.0f, 3, 4, 7, scaleFactor);
        this.tailBase.func_78792_a(this.tailMiddle);
        (this.tailTip = new ModelRenderer((ModelBase)this, 24, 3)).func_78790_a(-1.5f, -4.5f, 9.0f, 3, 4, 7, scaleFactor);
        this.tailTip.field_78795_f = -0.2618004f;
        this.tailMiddle.func_78792_a(this.tailTip);
        this.body.func_78792_a(this.tailBase);
        (this.tailThin = new ModelRenderer((ModelBase)this, 116, 0)).func_78790_a(-0.5f, 0.0f, 0.5f, 1, 5, 1, scaleFactor);
        this.tailThin.func_78793_a(0.0f, -6.0f, 4.0f);
        this.tailThin.field_78795_f = -1.134464f;
        (this.tailTuft = new ModelRenderer((ModelBase)this, 120, 0)).func_78790_a(-1.0f, 0.0f, 0.25f, 2, 6, 2, scaleFactor);
        this.tailTuft.func_78793_a(0.0f, 5.0f, 0.0f);
        this.tailThin.func_78792_a(this.tailTuft);
        this.body.func_78792_a(this.tailThin);
        (this.backLeftLeg = new ModelRenderer((ModelBase)this, 78, 29)).func_78790_a(-2.5f, -2.0f, -2.5f, 4, 9, 5, scaleFactor);
        this.backLeftLeg.func_78793_a(4.0f, 9.0f, 11.0f);
        (this.backLeftShin = new ModelRenderer((ModelBase)this, 78, 43)).func_78790_a(-2.0f, 0.0f, -1.0f, 3, 5, 3, scaleFactor);
        this.backLeftShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.backLeftLeg.func_78792_a(this.backLeftShin);
        (this.backLeftHoof = new ModelRenderer((ModelBase)this, 78, 51)).func_78790_a(-2.5f, 5.1f, -2.0f, 4, 3, 4, scaleFactor);
        this.backLeftShin.func_78792_a(this.backLeftHoof);
        (this.backRightLeg = new ModelRenderer((ModelBase)this, 96, 29)).func_78790_a(-1.5f, -2.0f, -2.5f, 4, 9, 5, scaleFactor);
        this.backRightLeg.func_78793_a(-4.0f, 9.0f, 11.0f);
        (this.backRightShin = new ModelRenderer((ModelBase)this, 96, 43)).func_78790_a(-1.0f, 0.0f, -1.0f, 3, 5, 3, scaleFactor);
        this.backRightShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.backRightLeg.func_78792_a(this.backRightShin);
        (this.backRightHoof = new ModelRenderer((ModelBase)this, 96, 51)).func_78790_a(-1.5f, 5.1f, -1.5f, 4, 3, 4, scaleFactor);
        this.backRightShin.func_78792_a(this.backRightHoof);
        (this.frontLeftLeg = new ModelRenderer((ModelBase)this, 44, 29)).func_78790_a(-1.9f, -1.0f, -2.1f, 3, 8, 4, scaleFactor);
        this.frontLeftLeg.func_78793_a(4.0f, 9.0f, -8.0f);
        (this.frontLeftShin = new ModelRenderer((ModelBase)this, 44, 41)).func_78790_a(-1.9f, 0.0f, -1.6f, 3, 5, 3, scaleFactor);
        this.frontLeftShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.frontLeftLeg.func_78792_a(this.frontLeftShin);
        (this.frontLeftHoof = new ModelRenderer((ModelBase)this, 44, 51)).func_78790_a(-2.4f, 5.1f, -2.1f, 4, 3, 4, scaleFactor);
        this.frontLeftShin.func_78792_a(this.frontLeftHoof);
        (this.frontRightLeg = new ModelRenderer((ModelBase)this, 60, 29)).func_78790_a(-1.1f, -1.0f, -2.1f, 3, 8, 4, scaleFactor);
        this.frontRightLeg.func_78793_a(-4.0f, 9.0f, -8.0f);
        (this.frontRightShin = new ModelRenderer((ModelBase)this, 60, 41)).func_78790_a(-1.1f, 0.0f, -1.6f, 3, 5, 3, scaleFactor);
        this.frontRightShin.func_78793_a(0.0f, 7.0f, 0.0f);
        this.frontRightLeg.func_78792_a(this.frontRightShin);
        (this.frontRightHoof = new ModelRenderer((ModelBase)this, 60, 51)).func_78790_a(-1.6f, 5.1f, -2.1f, 4, 3, 4, scaleFactor);
        this.frontRightShin.func_78792_a(this.frontRightHoof);
        (this.head = new ModelRenderer((ModelBase)this, 0, 0)).func_78790_a(-2.5f, -10.0f, -1.5f, 5, 5, 7, scaleFactor);
        this.head.func_78793_a(0.0f, 4.0f, -10.0f);
        this.head.field_78795_f = 0.5235988f;
        (this.upperMouth = new ModelRenderer((ModelBase)this, 24, 18)).func_78790_a(-2.0f, -10.0f, -7.0f, 4, 3, 6, scaleFactor);
        (this.lowerMouth = new ModelRenderer((ModelBase)this, 24, 27)).func_78790_a(-2.0f, -7.0f, -6.5f, 4, 2, 5, scaleFactor);
        this.head.func_78792_a(this.upperMouth);
        this.head.func_78792_a(this.lowerMouth);
        (this.horseLeftEar = new ModelRenderer((ModelBase)this, 0, 0)).func_78790_a(0.45f, -12.0f, 4.0f, 2, 3, 1, scaleFactor);
        (this.horseRightEar = new ModelRenderer((ModelBase)this, 0, 0)).func_78790_a(-2.45f, -12.0f, 4.0f, 2, 3, 1, scaleFactor);
        (this.muleLeftEar = new ModelRenderer((ModelBase)this, 0, 12)).func_78790_a(-2.0f, -16.0f, 4.0f, 2, 7, 1, scaleFactor);
        this.muleLeftEar.field_78808_h = 0.2617994f;
        (this.muleRightEar = new ModelRenderer((ModelBase)this, 0, 12)).func_78790_a(0.0f, -16.0f, 4.0f, 2, 7, 1, scaleFactor);
        this.muleRightEar.field_78808_h = -0.2617994f;
        this.head.func_78792_a(this.horseLeftEar);
        this.head.func_78792_a(this.horseRightEar);
        this.head.func_78792_a(this.muleLeftEar);
        this.head.func_78792_a(this.muleRightEar);
        (this.neck = new ModelRenderer((ModelBase)this, 0, 12)).func_78790_a(-2.05f, -9.8f, -2.0f, 4, 14, 8, scaleFactor);
        this.neck.func_78793_a(0.0f, 4.0f, -10.0f);
        this.neck.field_78795_f = 0.5235988f;
        (this.muleLeftChest = new ModelRenderer((ModelBase)this, 0, 34)).func_78790_a(-3.0f, 0.0f, 0.0f, 8, 8, 3, scaleFactor);
        this.muleLeftChest.func_78793_a(-7.5f, 3.0f, 10.0f);
        this.muleLeftChest.field_78796_g = 1.5707964f;
        (this.muleRightChest = new ModelRenderer((ModelBase)this, 0, 47)).func_78790_a(-3.0f, 0.0f, 0.0f, 8, 8, 3, scaleFactor);
        this.muleRightChest.func_78793_a(4.5f, 3.0f, 10.0f);
        this.muleRightChest.field_78796_g = 1.5707964f;
        final ModelRenderer horseSaddleBottom = new ModelRenderer((ModelBase)this, 80, 0);
        horseSaddleBottom.func_78790_a(-5.0f, 0.0f, -3.0f, 10, 1, 8, scaleFactor);
        horseSaddleBottom.func_78793_a(0.0f, -9.0f, -7.0f);
        this.body.func_78792_a(horseSaddleBottom);
        final ModelRenderer horseSaddleFront = new ModelRenderer((ModelBase)this, 106, 9);
        horseSaddleFront.func_78790_a(-1.5f, -1.0f, -3.0f, 3, 1, 2, scaleFactor);
        horseSaddleBottom.func_78792_a(horseSaddleFront);
        final ModelRenderer horseSaddleBack = new ModelRenderer((ModelBase)this, 80, 9);
        horseSaddleBack.func_78790_a(-4.0f, -1.0f, 3.0f, 8, 1, 2, scaleFactor);
        horseSaddleBottom.func_78792_a(horseSaddleBack);
        (this.horseLeftSaddleRope = new ModelRenderer((ModelBase)this, 70, 0)).func_78790_a(-0.5f, 0.0f, -0.5f, 1, 6, 1, scaleFactor);
        this.horseLeftSaddleRope.func_78793_a(5.0f, 1.0f, 0.0f);
        horseSaddleBottom.func_78792_a(this.horseLeftSaddleRope);
        final ModelRenderer horseLeftSaddleMetal = new ModelRenderer((ModelBase)this, 74, 0);
        horseLeftSaddleMetal.func_78790_a(-0.5f, 6.0f, -1.0f, 1, 2, 2, scaleFactor);
        this.horseLeftSaddleRope.func_78792_a(horseLeftSaddleMetal);
        (this.horseRightSaddleRope = new ModelRenderer((ModelBase)this, 80, 0)).func_78790_a(-0.5f, 0.0f, -0.5f, 1, 6, 1, scaleFactor);
        this.horseRightSaddleRope.func_78793_a(-5.0f, 1.0f, 0.0f);
        horseSaddleBottom.func_78792_a(this.horseRightSaddleRope);
        final ModelRenderer horseRightSaddleMetal = new ModelRenderer((ModelBase)this, 74, 4);
        horseRightSaddleMetal.func_78790_a(-0.5f, 6.0f, -1.0f, 1, 2, 2, scaleFactor);
        this.horseRightSaddleRope.func_78792_a(horseRightSaddleMetal);
        final ModelRenderer horseLeftFaceMetal = new ModelRenderer((ModelBase)this, 74, 13);
        horseLeftFaceMetal.func_78790_a(1.5f, -8.0f, -4.0f, 1, 2, 2, scaleFactor);
        this.head.func_78792_a(horseLeftFaceMetal);
        final ModelRenderer horseRightFaceMetal = new ModelRenderer((ModelBase)this, 74, 13);
        horseRightFaceMetal.func_78790_a(-2.5f, -8.0f, -4.0f, 1, 2, 2, scaleFactor);
        this.head.func_78792_a(horseRightFaceMetal);
        (this.horseFaceRopes = new ModelRenderer((ModelBase)this, 80, 12)).func_78790_a(-2.5f, -10.1f, -7.0f, 5, 5, 12, 0.2f);
        this.head.func_78792_a(this.horseFaceRopes);
        this.tackArray = new ModelRenderer[] { horseSaddleBottom, horseSaddleFront, horseSaddleBack, horseLeftSaddleMetal, this.horseLeftSaddleRope, horseRightSaddleMetal, this.horseRightSaddleRope, horseLeftFaceMetal, horseRightFaceMetal, this.horseFaceRopes };
        final ModelRenderer horseLeftRein = new ModelRenderer((ModelBase)this, 44, 10);
        horseLeftRein.func_78789_a(2.6f, -6.0f, -6.0f, 0, 3, 16);
        final ModelRenderer horseRightRein = new ModelRenderer((ModelBase)this, 44, 5);
        horseRightRein.func_78789_a(-2.6f, -6.0f, -6.0f, 0, 3, 16);
        this.neck.func_78792_a(horseLeftRein);
        this.neck.func_78792_a(horseRightRein);
        horseLeftRein.field_78795_f = -0.5235988f;
        horseRightRein.field_78795_f = -0.5235988f;
        this.extraTackArray = new ModelRenderer[] { horseLeftRein, horseRightRein };
        (this.mane = new ModelRenderer((ModelBase)this, 58, 0)).func_78790_a(-1.0f, -11.5f, 5.0f, 2, 16, 4, scaleFactor);
        this.mane.func_78793_a(0.0f, 0.0f, -1.0f);
        this.neck.func_78792_a(this.mane);
        final int hornLength = 7;
        (this.horn = new ModelRenderer((ModelBase)this, 84, 0)).func_78790_a(-0.5f, -10.0f - hornLength, 2.0f, 1, hornLength, 1, scaleFactor);
        this.head.func_78792_a(this.horn);
        final int babyHornLength = 3;
        (this.babyHorn = new ModelRenderer((ModelBase)this, 84, 0)).func_78790_a(-0.5f, -10.0f - babyHornLength, 2.0f, 1, babyHornLength, 1, scaleFactor);
        this.head.func_78792_a(this.babyHorn);
    }
    
    public void updateBoxes(final AbstractHorse entityIn) {
        if (entityIn instanceof AbstractHorseGenetic) {
            final AbstractHorseGenetic horse = (AbstractHorseGenetic)entityIn;
            this.muleLeftEar.field_78806_j = horse.longEars();
            this.muleRightEar.field_78806_j = horse.longEars();
            this.horseLeftEar.field_78806_j = !horse.longEars();
            this.horseRightEar.field_78806_j = !horse.longEars();
            this.tailBase.field_78806_j = horse.fluffyTail();
            this.tailThin.field_78806_j = !horse.fluffyTail();
            this.ageScale = horse.getGangliness();
        }
        else {
            System.out.println("Attempting to use HorseGeneticModel on an unsupported entity type");
        }
        this.horn.field_78806_j = false;
        this.babyHorn.field_78806_j = false;
        boolean hasChest = false;
        if (entityIn instanceof AbstractChestHorse) {
            hasChest = ((AbstractChestHorse)entityIn).func_190695_dh();
        }
        this.muleLeftChest.field_78806_j = hasChest;
        this.muleRightChest.field_78806_j = hasChest;
        final boolean isSaddled = entityIn.func_110257_ck();
        final boolean isRidden = entityIn.func_184207_aI();
        for (final ModelRenderer tack_piece : this.tackArray) {
            tack_piece.field_78806_j = isSaddled;
        }
        for (final ModelRenderer extra_tack : this.extraTackArray) {
            extra_tack.field_78806_j = (isRidden && isSaddled);
        }
        this.body.field_78797_d = 11.0f;
    }
    
    private float updateHorseRotation(final float prevRotation, final float currentRotation, final float partialTickTime) {
        float bodyRotation;
        for (bodyRotation = currentRotation - prevRotation; bodyRotation < -180.0f; bodyRotation += 360.0f) {}
        while (bodyRotation >= 180.0f) {
            bodyRotation -= 360.0f;
        }
        return prevRotation + partialTickTime * bodyRotation;
    }
    
    public void func_78088_a(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        if (!(entityIn instanceof AbstractHorse)) {
            System.err.println("Horse model can only be used on an abstract horse");
        }
        this.updateBoxes((AbstractHorse)entityIn);
        final float f = (this.head.field_78795_f - 0.5235988f) * 0.6031135f;
        if (this.field_78091_s) {
            GlStateManager.func_179094_E();
            GlStateManager.func_179152_a(this.ageScale, 0.5f + this.ageScale * 0.5f, this.ageScale);
            GlStateManager.func_179109_b(0.0f, 0.95f * (1.0f - this.ageScale), 0.0f);
        }
        this.backLeftLeg.func_78785_a(scale);
        this.backRightLeg.func_78785_a(scale);
        this.frontLeftLeg.func_78785_a(scale);
        this.frontRightLeg.func_78785_a(scale);
        if (this.field_78091_s) {
            GlStateManager.func_179121_F();
            GlStateManager.func_179094_E();
            GlStateManager.func_179109_b(0.0f, this.ageScale * 1.35f * (1.0f - this.ageScale), 0.0f);
            GlStateManager.func_179152_a(this.ageScale, this.ageScale, this.ageScale);
        }
        this.body.func_78785_a(scale);
        this.neck.func_78785_a(scale);
        if (this.field_78091_s) {
            GlStateManager.func_179121_F();
            GlStateManager.func_179094_E();
            final float headScale = 0.5f + this.ageScale * this.ageScale * 0.5f;
            GlStateManager.func_179109_b(0.0f, this.ageScale * 1.35f * (1.0f - this.ageScale), 0.0f);
            GlStateManager.func_179152_a(headScale, headScale, headScale);
            final float extra = (headScale - this.ageScale) * 1.35f * (1.0f - this.ageScale);
            GlStateManager.func_179109_b(0.0f, extra * (float)Math.cos(this.head.field_78795_f), extra * (float)Math.sin(this.head.field_78795_f));
        }
        this.head.func_78785_a(scale);
        if (this.field_78091_s) {
            GlStateManager.func_179121_F();
        }
        this.muleLeftChest.func_78785_a(scale);
        this.muleRightChest.func_78785_a(scale);
    }
    
    private void setMouthAnimations(final float mouthOpenAmount) {
        this.upperMouth.field_78797_d = 0.02f;
        this.lowerMouth.field_78797_d = 0.0f;
        this.upperMouth.field_78798_e = 0.02f - mouthOpenAmount;
        this.lowerMouth.field_78798_e = mouthOpenAmount;
        this.upperMouth.field_78795_f = -0.09424778f * mouthOpenAmount;
        this.lowerMouth.field_78795_f = 0.15707964f * mouthOpenAmount;
        this.upperMouth.field_78796_g = 0.0f;
        this.lowerMouth.field_78796_g = 0.0f;
    }
    
    public void func_78086_a(final EntityLivingBase entityIn, final float limbSwing, final float limbSwingAmount, final float partialTickTime) {
        super.func_78086_a(entityIn, limbSwing, limbSwingAmount, partialTickTime);
        final float bodyRotation = this.updateHorseRotation(entityIn.field_70760_ar, entityIn.field_70761_aq, partialTickTime);
        final float headRotation = this.updateHorseRotation(entityIn.field_70758_at, entityIn.field_70759_as, partialTickTime);
        final float interpolatedPitch = entityIn.field_70127_C + (entityIn.field_70125_A - entityIn.field_70127_C) * partialTickTime;
        float headRelativeRotation = headRotation - bodyRotation;
        float f4 = interpolatedPitch * 0.017453292f;
        if (headRelativeRotation > 20.0f) {
            headRelativeRotation = 20.0f;
        }
        if (headRelativeRotation < -20.0f) {
            headRelativeRotation = -20.0f;
        }
        if (limbSwingAmount > 0.2f) {
            f4 += MathHelper.func_76134_b(limbSwing * 0.4f) * 0.15f * limbSwingAmount;
        }
        final AbstractHorse abstracthorse = (AbstractHorse)entityIn;
        final float grassEatingAmount = abstracthorse.func_110258_o(partialTickTime);
        final float rearingAmount = abstracthorse.func_110223_p(partialTickTime);
        final float notRearingAmount = 1.0f - rearingAmount;
        final boolean isSwishingTail = abstracthorse.field_110278_bp != 0;
        final boolean isSaddled = abstracthorse.func_110257_ck();
        final boolean isBeingRidden = abstracthorse.func_184207_aI();
        final float ticks = entityIn.field_70173_aa + partialTickTime;
        final float legRotationBase = MathHelper.func_76134_b(limbSwing * 0.6662f + 3.1415927f);
        final float legRotation1 = legRotationBase * 0.8f * limbSwingAmount;
        final float neckBend = rearingAmount + 1.0f - Math.max(rearingAmount, grassEatingAmount);
        final float mouthOpenAmount = abstracthorse.func_110201_q(partialTickTime);
        this.setMouthAnimations(mouthOpenAmount);
        this.neck.func_78793_a(0.0f, 4.0f, -10.0f);
        this.neck.field_78795_f = rearingAmount * (0.2617994f + f4) + grassEatingAmount * 2.1816616f + (1.0f - Math.max(rearingAmount, grassEatingAmount)) * 0.5235988f + f4;
        this.neck.field_78796_g = neckBend * headRelativeRotation * 0.017453292f;
        this.neck.field_78797_d = rearingAmount * -6.0f + grassEatingAmount * 11.0f + (1.0f - Math.max(rearingAmount, grassEatingAmount)) * this.neck.field_78797_d;
        this.neck.field_78798_e = rearingAmount * -1.0f + grassEatingAmount * -10.0f + (1.0f - Math.max(rearingAmount, grassEatingAmount)) * this.neck.field_78798_e;
        this.body.field_78795_f = rearingAmount * -0.7853982f;
        this.head.field_78800_c = this.neck.field_78800_c;
        this.head.field_78797_d = this.neck.field_78797_d;
        this.head.field_78798_e = this.neck.field_78798_e;
        this.head.field_78795_f = this.neck.field_78795_f;
        this.head.field_78796_g = this.neck.field_78796_g;
        final float legRotationRearing = 0.2617994f * rearingAmount;
        final float legRotationTicks = MathHelper.func_76134_b(ticks * 0.6f + 3.1415927f);
        this.frontLeftLeg.field_78797_d = -2.0f * rearingAmount + 9.0f * notRearingAmount;
        this.frontLeftLeg.field_78798_e = -2.0f * rearingAmount + -8.0f * notRearingAmount;
        this.frontRightLeg.field_78797_d = this.frontLeftLeg.field_78797_d;
        this.frontRightLeg.field_78798_e = this.frontLeftLeg.field_78798_e;
        final float legRotation2 = (-1.0471976f + legRotationTicks) * rearingAmount + legRotation1 * notRearingAmount;
        final float legRotation3 = (-1.0471976f - legRotationTicks) * rearingAmount + -legRotation1 * notRearingAmount;
        this.backLeftLeg.field_78795_f = legRotationRearing + -legRotationBase * 0.5f * limbSwingAmount * notRearingAmount;
        this.backRightLeg.field_78795_f = legRotationRearing + legRotationBase * 0.5f * limbSwingAmount * notRearingAmount;
        this.frontLeftLeg.field_78795_f = legRotation2;
        this.frontLeftShin.field_78795_f = (this.frontLeftLeg.field_78795_f + 3.1415927f * Math.max(0.0f, 0.2f + legRotationTicks * 0.2f)) * rearingAmount + (legRotation1 + Math.max(0.0f, legRotationBase * 0.5f * limbSwingAmount)) * notRearingAmount - this.frontLeftLeg.field_78795_f;
        this.frontRightLeg.field_78795_f = legRotation3;
        this.frontRightShin.field_78795_f = (this.frontRightLeg.field_78795_f + 3.1415927f * Math.max(0.0f, 0.2f - legRotationTicks * 0.2f)) * rearingAmount + (-legRotation1 + Math.max(0.0f, -legRotationBase * 0.5f * limbSwingAmount)) * notRearingAmount - this.frontRightLeg.field_78795_f;
        this.muleRightChest.field_78797_d = 3.0f;
        this.muleRightChest.field_78798_e = 10.0f;
        this.muleRightChest.field_78797_d = rearingAmount * 5.5f + notRearingAmount * this.muleRightChest.field_78797_d;
        this.muleRightChest.field_78798_e = rearingAmount * 15.0f + notRearingAmount * this.muleRightChest.field_78798_e;
        this.muleLeftChest.field_78795_f = legRotation1 / 5.0f;
        this.muleRightChest.field_78795_f = -legRotation1 / 5.0f;
        if (isSaddled) {
            this.muleLeftChest.field_78797_d = this.muleRightChest.field_78797_d;
            this.muleLeftChest.field_78798_e = this.muleRightChest.field_78798_e;
            if (isBeingRidden) {
                this.horseLeftSaddleRope.field_78795_f = -1.0471976f;
                this.horseRightSaddleRope.field_78795_f = -1.0471976f;
                this.horseLeftSaddleRope.field_78808_h = 0.0f;
                this.horseRightSaddleRope.field_78808_h = 0.0f;
            }
            else {
                this.horseLeftSaddleRope.field_78795_f = legRotation1 / 3.0f;
                this.horseRightSaddleRope.field_78795_f = legRotation1 / 3.0f;
                this.horseLeftSaddleRope.field_78808_h = legRotation1 / 5.0f;
                this.horseRightSaddleRope.field_78808_h = -legRotation1 / 5.0f;
            }
        }
        float tailRotation = -1.3089969f + limbSwingAmount * 1.5f;
        float donkeyTailRotate = 0.17f + limbSwingAmount;
        if (tailRotation > 0.0f) {
            tailRotation = 0.0f;
        }
        if (isSwishingTail) {
            this.tailBase.field_78796_g = MathHelper.func_76134_b(ticks * 0.7f);
            this.tailThin.field_78796_g = MathHelper.func_76134_b(ticks * 0.7f);
            tailRotation = 0.0f;
            donkeyTailRotate = 1.5707964f;
        }
        else {
            this.tailBase.field_78796_g = 0.0f;
            this.tailThin.field_78796_g = 0.0f;
        }
        this.tailBase.field_78795_f = tailRotation;
        this.tailThin.field_78795_f = donkeyTailRotate;
        if (abstracthorse instanceof AbstractHorseGenetic && ((AbstractHorseGenetic)abstracthorse).thinMane()) {
            this.mane.field_78798_e = -1.0f;
        }
        else {
            this.mane.field_78798_e = 0.0f;
        }
    }
}
