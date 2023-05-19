package cx.rain.mc.gendermod.fabric.data.provider.language;

import cx.rain.mc.gendermod.effect.GModEffects;
import cx.rain.mc.gendermod.stat.GModStats;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class GLanguageProviderENUS extends FabricLanguageProvider {
    public GLanguageProviderENUS(FabricDataOutput dataOutput) {
        super(dataOutput, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(GModEffects.GENDER_TRANSFORM.get(), "Gender transformation");

        translationBuilder.add("item.gendermod.transgender_potion.desc", "Change gender of user, only can be used once");
        translationBuilder.add("item.gendermod.gender_transform_potion.effect.gender_transform", "Potion of Gender Transformation");
        translationBuilder.add("item.gendermod.gender_transform_splash_potion.effect.gender_transform", "Splash Potion of Gender Transformation");
        translationBuilder.add("item.gendermod.gender_transform_lingering_potion.effect.gender_transform", "Lingering Potion of Gender Transformation");

        translationBuilder.add(GModStats.GENDER_TRANSITIONS.get(), "Gender transformations");
    }
}
