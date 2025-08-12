package sekelsta.horse_colors.client;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import sekelsta.horse_colors.genetics.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.resources.*;
import java.util.*;
import java.io.*;
import net.minecraft.client.*;

@SideOnly(Side.CLIENT)
public class GeneBookScreen extends GuiScreen
{
    private static final ResourceLocation BOOK_GUI_TEXTURES;
    private static final int linesPerPage = 14;
    private static final int lineWrapWidth = 124;
    private static final int bookWidth = 360;
    private static final int bookHeight = 192;
    private static final int pageWidth = 130;
    private static final int pageCrease = 10;
    int currPage;
    private Genome genome;
    private List<List<String>> contents;
    private List<String> pages;
    private ChangePageButton buttonNextPage;
    private ChangePageButton buttonPreviousPage;
    private GuiButton buttonDone;
    private final boolean pageTurnSounds = true;
    
    public GeneBookScreen(final Genome genomeIn) {
        this.currPage = 0;
        this.genome = genomeIn;
    }
    
    public void func_73863_a(final int mouseX, final int mouseY, final float partialTicks) {
        GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
        this.field_146297_k.func_110434_K().func_110577_a(GeneBookScreen.BOOK_GUI_TEXTURES);
        final int x = (this.field_146294_l - 360) / 2;
        final int y = 2;
        func_146110_a(x, y, 0.0f, 0.0f, 360, 192, 512.0f, 256.0f);
        this.renderPage(this.currPage, this.field_146294_l / 2 - 130 - 10);
        if (this.getPageCount() > this.currPage + 1) {
            this.renderPage(this.currPage + 1, this.field_146294_l / 2 + 10);
        }
        super.func_73863_a(mouseX, mouseY, partialTicks);
    }
    
    private void renderPage(final int pagenum, final int x) {
        final String pageindicator = I18n.func_135052_a("book.pageIndicator", new Object[] { pagenum + 1, this.getPageCount() });
        final String pagetext = this.getPageText(pagenum);
        final int j1 = this.getTextWidth(pageindicator);
        this.field_146289_q.func_78276_b(pageindicator, x - j1 + 130, 18, 0);
        this.field_146289_q.func_78279_b(pagetext, x, 32, 124, 0);
    }
    
    public void func_73866_w_() {
        this.contents = this.genome.getBookContents();
        this.pages = new ArrayList<String>();
        for (int ch = 0; ch < this.contents.size(); ++ch) {
            String s = "";
            int lines = 0;
            for (int ln = 0; ln < this.contents.get(ch).size(); ++ln) {
                final String text = this.contents.get(ch).get(ln);
                final int wrapped = this.field_146289_q.func_78271_c(text, 124).size();
                if (lines + wrapped > 14 && lines > 0) {
                    this.pages.add(s);
                    s = "";
                    lines = 0;
                }
                lines += wrapped;
                s = s + text + "\n";
            }
            this.pages.add(s);
        }
        this.addDoneButton();
        this.addChangePageButtons();
    }
    
    protected void addDoneButton() {
        this.buttonDone = this.func_189646_b(new GuiButton(0, this.field_146294_l / 2 - 100, 196, 200, 20, I18n.func_135052_a("gui.done", new Object[0])));
    }
    
    protected void addChangePageButtons() {
        final int x1 = (this.field_146294_l - 360) / 2;
        final int x2 = (this.field_146294_l + 360) / 2;
        final int j = 2;
        final int y = 159;
        this.buttonNextPage = (ChangePageButton)this.func_189646_b((GuiButton)new ChangePageButton(1, x2 - 43 - 24, y, true));
        this.buttonPreviousPage = (ChangePageButton)this.func_189646_b((GuiButton)new ChangePageButton(2, x1 + 43, y, false));
        this.updateButtons();
    }
    
    private int getPageCount() {
        return this.pages.size();
    }
    
    protected void previousPage() {
        this.currPage = Math.max(this.currPage - 2, 0);
        this.updateButtons();
    }
    
    protected void nextPage() {
        if (this.currPage < this.getPageCount() - 2) {
            this.currPage += 2;
        }
        this.updateButtons();
    }
    
    private void updateButtons() {
        this.buttonNextPage.field_146125_m = (this.currPage < this.getPageCount() - 2);
        this.buttonPreviousPage.field_146125_m = (this.currPage > 0);
    }
    
    private String getPageText(final int pagenum) {
        if (this.pages.size() > 0) {
            return this.pages.get(pagenum);
        }
        return "";
    }
    
    private int getTextWidth(final String text) {
        return this.field_146289_q.func_78256_a(text);
    }
    
    protected void func_146284_a(final GuiButton button) throws IOException {
        if (button.field_146124_l) {
            if (button.field_146127_k == 0) {
                this.field_146297_k.func_147108_a((GuiScreen)null);
            }
            else if (button.field_146127_k == 1) {
                this.nextPage();
            }
            else if (button.field_146127_k == 2) {
                this.previousPage();
            }
            this.updateButtons();
        }
    }
    
    static {
        BOOK_GUI_TEXTURES = new ResourceLocation("horse_colors:textures/gui/book.png");
    }
    
    @SideOnly(Side.CLIENT)
    static class ChangePageButton extends GuiButton
    {
        private final boolean isForward;
        
        public ChangePageButton(final int buttonId, final int x, final int y, final boolean isForwardIn) {
            super(buttonId, x, y, 23, 13, "");
            this.isForward = isForwardIn;
        }
        
        public void func_191745_a(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks) {
            if (this.field_146125_m) {
                final boolean flag = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
                GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
                mc.func_110434_K().func_110577_a(GeneBookScreen.BOOK_GUI_TEXTURES);
                int i = 0;
                int j = 192;
                if (flag) {
                    i += 23;
                }
                if (!this.isForward) {
                    j += 13;
                }
                func_146110_a(this.field_146128_h, this.field_146129_i, (float)i, (float)j, 23, 13, 512.0f, 256.0f);
            }
        }
    }
}
