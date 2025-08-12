package sekelsta.horse_colors.config;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.client.event.*;
import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.common.eventhandler.*;

@Config(modid = "horse_colors")
public class HorseConfig
{
    public static final Common COMMON;
    public static final Growth GROWTH;
    public static final Breeding BREEDING;
    public static final Genetics GENETICS;
    
    public static boolean getEnableGroundTie() {
        return HorseConfig.COMMON.enableGroundTie;
    }
    
    public static boolean getGrowsGradually() {
        return HorseConfig.GROWTH.foalsGrowGradually;
    }
    
    public static boolean getUseGeneticStats() {
        return HorseConfig.GENETICS.useGeneticStats;
    }
    
    public static boolean getEnableHealthEffects() {
        return HorseConfig.GENETICS.enableHealthEffects;
    }
    
    public static boolean getBookShowsGenes() {
        return HorseConfig.GENETICS.bookShowsGenes;
    }
    
    public static boolean getBookShowsTraits() {
        return HorseConfig.GENETICS.bookShowsTraits;
    }
    
    public static boolean blockVanillaHorseSpawns() {
        return HorseConfig.COMMON.blockVanillaHorseSpawns;
    }
    
    public static boolean blockVanillaDonkeySpawns() {
        return false;
    }
    
    public static int getYearLength() {
        return (int)(HorseConfig.GROWTH.yearLength * 24000.0);
    }
    
    public static boolean enableDebugInfo() {
        return HorseConfig.COMMON.horseDebugInfo;
    }
    
    public static double getMutationChance() {
        return HorseConfig.GENETICS.mutationChance;
    }
    
    public static int getMinAge() {
        return HorseConfig.GROWTH.getMinAge();
    }
    
    public static double getMaxChildGrowth() {
        return 1.0;
    }
    
    public static boolean getAutoEquipSaddle() {
        return HorseConfig.COMMON.autoEquipSaddle;
    }
    
    public static boolean convertVanillaHorses() {
        return HorseConfig.COMMON.convertVanillaHorses;
    }
    
    public static boolean isGenderEnabled() {
        return HorseConfig.BREEDING.enableGenders;
    }
    
    public static int getHorseRebreedTicks(final boolean isMale) {
        if (!isGenderEnabled()) {
            return HorseConfig.BREEDING.genderlessBreedingCooldown;
        }
        if (isMale) {
            return HorseConfig.BREEDING.maleBreedingCooldown;
        }
        return Math.max(HorseConfig.BREEDING.femaleBreedingCooldown, getHorsePregnancyLength());
    }
    
    public static int getHorseBirthAge() {
        return HorseConfig.GROWTH.getMinAge();
    }
    
    public static boolean isPregnancyEnabled() {
        return isGenderEnabled();
    }
    
    public static int getHorsePregnancyLength() {
        return HorseConfig.BREEDING.pregnancyLength;
    }
    
    static {
        COMMON = new Common();
        GROWTH = new Growth();
        BREEDING = new Breeding();
        GENETICS = new Genetics();
    }
    
    public static class Common
    {
        @Config.Comment({ "If enabled, debugging information will appear on the screen when the", "player is holding a stick in their left hand and looks at a horse.", "For most users, it is probably better to leave this as false." })
        @Config.LangKey("horse_colors.config.common.horseDebugInfo")
        public boolean horseDebugInfo;
        @Config.Comment({ "If enabled, horses will not wander off if they are wearing a saddle." })
        @Config.LangKey("horse_colors.config.common.enableGroundTie")
        public boolean enableGroundTie;
        @Config.Comment({ "If enabled, right clicking a horse while holding a saddle or horse armor", "will equip it (as long as the horse isn't already wearing something in that slot)", "instead of opening the inventory." })
        @Config.LangKey("horse_colors.config.common.autoEquipSaddle")
        public boolean autoEquipSaddle;
        @Config.Comment({ "If set to true, only horses created by this mod will spawn.", "This mainly affects newly generated areas." })
        public boolean blockVanillaHorseSpawns;
        @Config.Comment({ "If enabled, each vanilla horse will be replaced by a horse", "from this mod.", "This matters for worlds where vanilla horses have already spawned", "or will spawn." })
        public boolean convertVanillaHorses;
        @Config.Comment({ "Larger numbers make horses more common, smaller numbers make them less common.", "1.0 makes them as common as in vanilla." })
        @Config.RangeDouble(min = 0.0, max = 1000.0)
        public double horseSpawnMultiplier;
        
        public Common() {
            this.horseDebugInfo = false;
            this.enableGroundTie = false;
            this.autoEquipSaddle = true;
            this.blockVanillaHorseSpawns = true;
            this.convertVanillaHorses = false;
            this.horseSpawnMultiplier = 1.0;
        }
    }
    
    public static class Growth
    {
        @Config.Comment({ "If enabled, gray hores will be born colored and their fur will gradually turn white." })
        public boolean grayGradually;
        @Config.Comment({ "How long a year lasts in twenty minute Minecraft days, for age-dependent colors such as gray." })
        @Config.RangeDouble(min = 0.0, max = 10000.0)
        public double yearLength;
        @Config.Comment({ "If enabled, foals will slowly get bigger as they grow into adults." })
        public boolean foalsGrowGradually;
        @Config.Comment({ "The number of twenty minute Minecraft days that it takes for a foal to become an adult." })
        public double growTime;
        
        public Growth() {
            this.grayGradually = true;
            this.yearLength = 2.0;
            this.foalsGrowGradually = false;
            this.growTime = 1.0;
        }
        
        public int getMinAge() {
            return (int)(this.growTime * -24000.0);
        }
        
        public int getMaxAge() {
            return (int)(360000.0 * this.yearLength);
        }
    }
    
    public static class Breeding
    {
        @Config.Comment({ "Enables or disables all features relating to gender." })
        public boolean enableGenders;
        @Config.Comment({ "The number of ticks until horses can breed again, when genders are disabled.", "The vanilla value is 6000 (or at 20 ticks per second, 5 minutes,", "or at 24000 ticks per minecraft day, 1/4 day)" })
        @Config.RangeInt(min = 0)
        public int genderlessBreedingCooldown;
        @Config.Comment({ "The number of ticks until male horses can breed again.", "The default value is 240 ticks (12 seconds)." })
        @Config.RangeInt(min = 0)
        public int maleBreedingCooldown;
        @Config.Comment({ "The number of ticks until female horses can breed again.", "The default value is 24000 ticks (20 minutes, or 1 minecraft day).", "This must always be at least as long as pregnancyLength." })
        @Config.RangeInt(min = 0)
        public int femaleBreedingCooldown;
        @Config.Comment({ "If genders are enabled, females will be pregnant for this many ticks.", "The default value is 24000 ticks (20 minutes, or 1 minecraft day).", "To disable pregnancy altogether, set this number to 0.", "Lowering this will not let female horses breed again sooner unless you", "also lower femaleRebreedTicks" })
        @Config.RangeInt(min = 0)
        public int pregnancyLength;
        
        public Breeding() {
            this.enableGenders = false;
            this.genderlessBreedingCooldown = 6000;
            this.maleBreedingCooldown = 240;
            this.femaleBreedingCooldown = 24000;
            this.pregnancyLength = 24000;
        }
    }
    
    public static class Genetics
    {
        @Config.Comment({ "If enabled, horses' speed, jump, and health will be determined", "through genetics instead of the default Minecraft way" })
        @Config.LangKey("horse_colors.config.common.useGeneticStats")
        public boolean useGeneticStats;
        @Config.Comment({ "If enabled, certain genes will have a small impact on health,", "as they do in real life. This does not prevent Overo Lethal", "White Syndrome." })
        @Config.LangKey("horse_colors.config.common.enableHealthEffects")
        public boolean enableHealthEffects;
        @Config.Comment({ "The chance for each allele to mutate. The recommended range", "is between 0.0001 and 0.01.", "To disable mutations, set this value to 0." })
        @Config.RangeDouble(min = 0.0, max = 1.0)
        public double mutationChance;
        @Config.Comment({ "Enable or disable genetic testing." })
        public boolean bookShowsGenes;
        @Config.Comment({ "Enable or disable physical inspection (rough information about health, ", "speed, and jump)." })
        public boolean bookShowsTraits;
        
        public Genetics() {
            this.useGeneticStats = true;
            this.enableHealthEffects = true;
            this.mutationChance = 5.0E-4;
            this.bookShowsGenes = true;
            this.bookShowsTraits = true;
        }
    }
    
    @Mod.EventBusSubscriber(modid = "horse_colors")
    private static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals("horse_colors")) {
                ConfigManager.sync("horse_colors", Config.Type.INSTANCE);
            }
        }
    }
}
