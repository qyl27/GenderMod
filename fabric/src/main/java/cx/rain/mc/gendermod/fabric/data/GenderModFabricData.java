package cx.rain.mc.gendermod.fabric.data;

import cx.rain.mc.gendermod.fabric.data.provider.GModelGenerator;
import cx.rain.mc.gendermod.fabric.data.provider.language.GLanguageProviderENUS;
import cx.rain.mc.gendermod.fabric.data.provider.language.GLanguageProviderZHCN;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GenderModFabricData implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        var pack = fabricDataGenerator.createPack();
        pack.addProvider(GModelGenerator::new);
        pack.addProvider(GLanguageProviderZHCN::new);
        pack.addProvider(GLanguageProviderENUS::new);
    }
}
