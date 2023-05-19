package cx.rain.mc.gendermod.fabric.data.provider;

import cx.rain.mc.gendermod.item.GModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;

public class GModelGenerator extends FabricModelProvider {
    public GModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(GModItems.GENDER_TRANSFORM_POTION.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(GModItems.GENDER_TRANSFORM_SPLASH_POTION.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(GModItems.GENDER_TRANSFORM_LINGERING_POTION.get(), ModelTemplates.FLAT_ITEM);
    }
}
