package sekelsta.horse_colors.item;

import net.minecraftforge.event.*;
import net.minecraft.item.*;
import net.minecraftforge.registries.*;
import net.minecraft.creativetab.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraftforge.client.model.*;

public class ModItems
{
    public static GeneBookItem geneBookItem;
    public static GenderChangeItem genderChangeItem;
    
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        (ModItems.geneBookItem = new GeneBookItem()).setRegistryName("horse_colors", "gene_book");
        ModItems.geneBookItem.func_77655_b(ModItems.geneBookItem.getRegistryName().toString());
        event.getRegistry().register((IForgeRegistryEntry)ModItems.geneBookItem);
        (ModItems.genderChangeItem = new GenderChangeItem()).setRegistryName("horse_colors", "gender_change_item");
        ModItems.genderChangeItem.func_77655_b(ModItems.genderChangeItem.getRegistryName().toString());
        ModItems.genderChangeItem.func_77637_a(CreativeTabs.field_78026_f);
        event.getRegistry().register((IForgeRegistryEntry)ModItems.genderChangeItem);
    }
    
    @SubscribeEvent
    public static void registerModels(final ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation((Item)ModItems.geneBookItem, 0, new ModelResourceLocation(ModItems.geneBookItem.getRegistryName(), "normal"));
        ModelLoader.setCustomModelResourceLocation((Item)ModItems.genderChangeItem, 0, new ModelResourceLocation(ModItems.genderChangeItem.getRegistryName(), "normal"));
    }
}
