package sekelsta.horse_colors;

import net.minecraftforge.common.*;
import sekelsta.horse_colors.entity.*;
import sekelsta.horse_colors.item.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import sekelsta.horse_colors.client.*;
import net.minecraftforge.fml.relauncher.*;
import org.apache.logging.log4j.*;

@Mod(modid = "horse_colors", name = "Realistic Horse Colors", version = "1.12.2-1.2.6", acceptedMinecraftVersions = "[1.12.2]")
public class HorseColors
{
    public static final String NAME = "Realistic Horse Colors";
    public static final String MODID = "horse_colors";
    public static final String VERSION = "1.12.2-1.2.6";
    @Mod.Instance("horse_colors")
    public static HorseColors instance;
    public static Logger logger;
    
    public HorseColors() {
        HorseColors.instance = this;
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)ModEntities.class);
        MinecraftForge.EVENT_BUS.register((Object)ModEntities.RegistrationHandler.class);
        MinecraftForge.EVENT_BUS.register((Object)ModItems.class);
        MinecraftForge.EVENT_BUS.register((Object)HorseReplacer.class);
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            this.clientSideSetup();
        }
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        ModEntities.onLoadComplete();
    }
    
    @SideOnly(Side.CLIENT)
    public void clientSideSetup() {
        ModEntities.registerRenderers();
        final HorseDebug hd = new HorseDebug();
        MinecraftForge.EVENT_BUS.register((Object)hd);
        MinecraftForge.EVENT_BUS.register((Object)HorseGui.class);
    }
    
    static {
        HorseColors.logger = LogManager.getLogger("horse_colors");
    }
}
