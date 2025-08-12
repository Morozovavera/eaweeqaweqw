package sekelsta.horse_colors;

import net.minecraft.entity.player.*;
import sekelsta.horse_colors.config.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import sekelsta.horse_colors.genetics.*;
import net.minecraft.entity.*;
import sekelsta.horse_colors.entity.*;
import sekelsta.horse_colors.renderer.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class HorseDebug
{
    public static boolean showDebug(final EntityPlayer player) {
        return HorseConfig.enableDebugInfo() && (showBasicDebug(player) || showGeneDebug(player));
    }
    
    public static boolean showBasicDebug(final EntityPlayer player) {
        final ItemStack itemStack = player.func_184592_cb();
        if (itemStack != null && itemStack.func_77973_b() == Items.field_151055_y) {
            return true;
        }
        final ItemStack inHand = player.func_184614_ca();
        return inHand != null && inHand.func_77973_b() == Items.field_151055_y;
    }
    
    public static boolean showGeneDebug(final EntityPlayer player) {
        final ItemStack itemStack = player.func_184592_cb();
        if (itemStack != null && itemStack.func_77973_b() == Items.field_190929_cY) {
            return true;
        }
        final ItemStack inHand = player.func_184614_ca();
        return inHand != null && inHand.func_77973_b() == Items.field_190929_cY;
    }
    
    public static ArrayList<String> debugNamedGenes(final Genome genome) {
        final ArrayList<String> list = new ArrayList<String>();
        for (final String gene : genome.listGenes()) {
            String s = gene + ": ";
            s = s + genome.getAllele(gene, 0) + ", ";
            s += genome.getAllele(gene, 1);
            list.add(s);
        }
        return list;
    }
    
    public static ArrayList<String> debugStatGenes(final Genome genome) {
        final ArrayList<String> list = new ArrayList<String>();
        for (String s : genome.listGenericChromosomes()) {
            final String stat = s;
            s = s + ": " + genome.countBits(genome.getChromosome(stat));
            s += " (";
            final int val = genome.getChromosome(stat);
            for (int i = 16; i > 0; --i) {
                s += (val >>> 2 * i - 1 & 0x1);
                s += (val >>> 2 * i - 2 & 0x1);
                if (i > 1) {
                    s += " ";
                }
            }
            s += ")";
            list.add(s);
        }
        return list;
    }
    
    private void addSubStats(final Genome genome, final List<String> list) {
        for (String s : genome.listStats()) {
            final String stat = s;
            s = s + ": " + genome.getStatValue(stat);
            s += " (";
            final int val = genome.getRawStat(stat);
            s += Genome.chrToStr(val);
            s += ")";
            list.add(s);
        }
    }
    
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void renderOverlayEvent(final RenderGameOverlayEvent.Text event) {
        final EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71439_g;
        if (!showDebug(player)) {
            return;
        }
        final RayTraceResult mouseOver = Minecraft.func_71410_x().field_71476_x;
        if (mouseOver != null && mouseOver.field_72308_g != null && mouseOver.field_72308_g instanceof IGeneticEntity) {
            final IGeneticEntity entity = (IGeneticEntity)mouseOver.field_72308_g;
            if (showGeneDebug(player)) {
                for (final String s : debugStatGenes(entity.getGenes())) {
                    event.getLeft().add(s);
                }
            }
            if (showBasicDebug(player) && entity instanceof EntityAgeable) {
                event.getLeft().add("Growing age: " + ((EntityAgeable)entity).func_70874_b());
            }
            if (showBasicDebug(player) && entity instanceof AbstractHorseGenetic) {
                event.getLeft().add("Display age: " + ((AbstractHorseGenetic)entity).getDisplayAge());
                event.getLeft().add("Pregnant since: " + ((AbstractHorseGenetic)entity).getPregnancyStart());
            }
            if (showBasicDebug(player)) {
                for (final TextureLayer l : entity.getGenes().getVariantTexturePaths()) {
                    if (l != null) {
                        event.getLeft().add(l.toString());
                    }
                }
            }
            if (showGeneDebug(player)) {
                for (final String s : debugNamedGenes(entity.getGenes())) {
                    event.getRight().add(s);
                }
            }
        }
    }
}
