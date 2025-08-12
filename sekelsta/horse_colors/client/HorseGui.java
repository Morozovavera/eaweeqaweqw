package sekelsta.horse_colors.client;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import sekelsta.horse_colors.entity.*;
import net.minecraft.util.text.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.passive.*;
import sekelsta.horse_colors.config.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraftforge.fml.common.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

@SideOnly(Side.CLIENT)
public class HorseGui extends GuiContainer
{
    private static final ResourceLocation HORSE_GUI_TEXTURES;
    private final IInventory playerInventory;
    private final IInventory horseInventory;
    private final AbstractHorseGenetic horseEntity;
    private float mousePosx;
    private float mousePosY;
    ITextComponent title;
    
    public HorseGui(final AbstractHorseGenetic horse) {
        super((Container)new ContainerHorseInventory((IInventory)Minecraft.func_71410_x().field_71439_g.field_71071_by, (IInventory)horse.getInventory(), (AbstractHorse)horse, (EntityPlayer)Minecraft.func_71410_x().field_71439_g));
        this.playerInventory = (IInventory)Minecraft.func_71410_x().field_71439_g.field_71071_by;
        this.horseInventory = (IInventory)horse.getInventory();
        this.horseEntity = horse;
        this.title = horse.func_145748_c_();
        this.field_146291_p = false;
    }
    
    protected void func_146979_b(final int mouseX, final int mouseY) {
        this.field_146289_q.func_78276_b(this.title.func_150254_d(), 8, 6, 4210752);
        this.field_146289_q.func_78276_b(this.playerInventory.func_145748_c_().func_150254_d(), 8, this.field_147000_g - 96 + 2, 4210752);
    }
    
    protected void func_146976_a(final float partialTicks, final int mouseX, final int mouseY) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(HorseGui.HORSE_GUI_TEXTURES);
        final int i = (this.field_146294_l - this.field_146999_f) / 2;
        final int j = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(i, j, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.horseEntity instanceof AbstractChestHorse) {
            final AbstractChestHorse abstractchestedhorseentity = this.horseEntity;
            if (abstractchestedhorseentity.func_190695_dh()) {
                this.func_73729_b(i + 79, j + 17, 0, this.field_147000_g, abstractchestedhorseentity.func_190696_dl() * 18, 54);
            }
        }
        if (this.horseEntity.func_190685_dA()) {
            this.func_73729_b(i + 7, j + 35 - 18, 18, this.field_147000_g + 54, 18, 18);
        }
        if (this.horseEntity.func_190677_dK()) {
            this.func_73729_b(i + 7, j + 35, 0, this.field_147000_g + 54, 18, 18);
        }
        if (HorseConfig.isGenderEnabled()) {
            final int iconWidth = 10;
            final int iconHeight = 11;
            int textureX = 176;
            int renderX = i + 157;
            final int renderY = j + 4;
            if (this.horseEntity.isMale()) {
                textureX += iconWidth;
            }
            int textureY = 0;
            if (this.horseEntity.isCastrated()) {
                textureY += iconHeight;
            }
            if (this.horseEntity.isPregnant()) {
                renderX -= 2;
                final int pregRenderX = renderX + iconWidth + 1;
                this.func_73729_b(pregRenderX, renderY + 1, 181, 23, 2, 10);
                final int pregnantAmount = (int)(11.0f * this.horseEntity.getPregnancyProgress());
                this.func_73729_b(pregRenderX, renderY + 11 - pregnantAmount, 177, 33 - pregnantAmount, 2, pregnantAmount);
            }
            this.func_73729_b(renderX, renderY, textureX, textureY, iconWidth, iconHeight);
        }
        GuiInventory.func_147046_a(i + 51, j + 60, 17, i + 51 - this.mousePosx, j + 75 - 50 - this.mousePosY, (EntityLivingBase)this.horseEntity);
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        this.func_146276_q_();
        this.mousePosx = (float)mouseX;
        this.mousePosY = (float)mouseY;
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.func_191948_b(mouseX, mouseY);
    }
    
    @SubscribeEvent
    public static void replaceGui(final GuiOpenEvent event) {
        if (event.getGui() instanceof GuiScreenHorseInventory) {
            final GuiScreenHorseInventory screen = (GuiScreenHorseInventory)event.getGui();
            final AbstractHorse horse = (AbstractHorse)ObfuscationReflectionHelper.getPrivateValue((Class)GuiScreenHorseInventory.class, (Object)screen, new String[] { "field_147034_x" });
            if (horse instanceof AbstractHorseGenetic) {
                final AbstractHorseGenetic horseGenetic = (AbstractHorseGenetic)horse;
                event.setGui((GuiScreen)new HorseGui(horseGenetic));
            }
        }
    }
    
    static {
        HORSE_GUI_TEXTURES = new ResourceLocation("horse_colors", "textures/gui/horse.png");
    }
}
