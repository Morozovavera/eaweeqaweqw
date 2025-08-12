package sekelsta.horse_colors.entity;

import net.minecraft.world.biome.*;
import net.minecraftforge.common.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import sekelsta.horse_colors.config.*;
import sekelsta.horse_colors.*;
import java.util.*;
import net.minecraft.client.*;
import sekelsta.horse_colors.renderer.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.registry.*;
import net.minecraftforge.event.*;
import net.minecraftforge.registries.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ModEntities
{
    private static int ID;
    static IForgeRegistry<Biome> biomeRegistry;
    public static EntityEntry HORSE_GENETIC;
    public static EntityEntry DONKEY_GENETIC;
    public static EntityEntry MULE_GENETIC;
    public static final int horseEggPrimary = 8340256;
    public static final int horseEggSecondary = 1117709;
    
    private static Biome[] getBiomes(final BiomeDictionary.Type type) {
        assert type != null;
        assert BiomeDictionary.getBiomes(type) != null;
        return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
    }
    
    public static void editSpawnTable() {
        final Collection<Biome> allBiomes = (Collection<Biome>)ModEntities.biomeRegistry.getValuesCollection();
        for (final Biome biome : allBiomes) {
            final List<Biome.SpawnListEntry> spawns = (List<Biome.SpawnListEntry>)biome.func_76747_a(EnumCreatureType.CREATURE);
            if (spawns.isEmpty()) {
                continue;
            }
            final ArrayList<Biome.SpawnListEntry> horseSpawns = new ArrayList<Biome.SpawnListEntry>();
            for (final Biome.SpawnListEntry entry : spawns) {
                if (entry.field_76300_b == EntityHorse.class && HorseConfig.blockVanillaHorseSpawns()) {
                    HorseColors.logger.debug("Removing vanilla horse spawn: " + entry + " from biome " + biome);
                    horseSpawns.add(entry);
                }
            }
            for (final Biome.SpawnListEntry horseSpawn : horseSpawns) {
                spawns.remove(horseSpawn);
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerRenderers() {
        final HorseGeneticRenderer renderer = new HorseGeneticRenderer(Minecraft.func_71410_x().func_175598_ae());
        RenderingRegistry.registerEntityRenderingHandler((Class)HorseGeneticEntity.class, (Render)renderer);
        RenderingRegistry.registerEntityRenderingHandler((Class)MuleGeneticEntity.class, (Render)renderer);
        RenderingRegistry.registerEntityRenderingHandler((Class)DonkeyGeneticEntity.class, (Render)renderer);
    }
    
    public static void onLoadComplete() {
        editSpawnTable();
    }
    
    static {
        ModEntities.ID = 0;
        ModEntities.HORSE_GENETIC = null;
        ModEntities.DONKEY_GENETIC = null;
        ModEntities.MULE_GENETIC = null;
        final int horsePlainsWeight = (int)Math.round(5.0 * HorseConfig.COMMON.horseSpawnMultiplier);
        final int horseSavannaWeight = (int)Math.round(1.0 * HorseConfig.COMMON.horseSpawnMultiplier);
        final ResourceLocation horseRegistryName = new ResourceLocation("horse_colors", "horse_felinoid");
        ModEntities.HORSE_GENETIC = EntityEntryBuilder.create().entity((Class)HorseGeneticEntity.class).id(horseRegistryName, ModEntities.ID++).name(horseRegistryName.toString()).egg(8340256, 1117709).tracker(64, 2, false).spawn(EnumCreatureType.CREATURE, horsePlainsWeight, 4, 6, (Iterable)BiomeDictionary.getBiomes(BiomeDictionary.Type.PLAINS)).spawn(EnumCreatureType.CREATURE, horseSavannaWeight, 4, 6, (Iterable)BiomeDictionary.getBiomes(BiomeDictionary.Type.SAVANNA)).build();
        final ResourceLocation donkeyRegistryName = new ResourceLocation("horse_colors", "donkey");
        ModEntities.DONKEY_GENETIC = EntityEntryBuilder.create().entity((Class)DonkeyGeneticEntity.class).id(donkeyRegistryName, ModEntities.ID++).name(donkeyRegistryName.toString()).tracker(64, 2, false).build();
        final ResourceLocation muleRegistryName = new ResourceLocation("horse_colors", "mule");
        ModEntities.MULE_GENETIC = EntityEntryBuilder.create().entity((Class)MuleGeneticEntity.class).id(muleRegistryName, ModEntities.ID++).name(muleRegistryName.toString()).tracker(64, 2, false).build();
    }
    
    public static class RegistrationHandler
    {
        @SubscribeEvent
        public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
            event.getRegistry().registerAll((IForgeRegistryEntry[])new EntityEntry[] { ModEntities.HORSE_GENETIC, ModEntities.DONKEY_GENETIC, ModEntities.MULE_GENETIC });
        }
        
        @SubscribeEvent
        public static void catchBiomeRegistry(final RegistryEvent.Register<Biome> event) {
            ModEntities.biomeRegistry = (IForgeRegistry<Biome>)event.getRegistry();
        }
    }
}
