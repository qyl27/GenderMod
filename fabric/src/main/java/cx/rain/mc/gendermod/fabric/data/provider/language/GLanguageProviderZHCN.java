package cx.rain.mc.gendermod.fabric.data.provider.language;

import cx.rain.mc.gendermod.effect.GModEffects;
import cx.rain.mc.gendermod.item.GModItems;
import cx.rain.mc.gendermod.stat.GModStats;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class GLanguageProviderZHCN extends FabricLanguageProvider {
    public GLanguageProviderZHCN(FabricDataOutput dataOutput) {
        super(dataOutput, "zh_cn");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(GModEffects.GENDER_TRANSFORM.get(), "改变性别");

        translationBuilder.add(GModItems.GENDER_TRANSFORM_POTION.get(), "变性药水");
        translationBuilder.add(GModItems.GENDER_TRANSFORM_SPLASH_POTION.get(), "喷溅型变性药水");
        translationBuilder.add(GModItems.GENDER_TRANSFORM_LINGERING_POTION.get(), "滞留型变性药水");

        translationBuilder.add("item.gendermod.transgender_potion.desc", "改变使用者的性别，只能使用一次");
        translationBuilder.add("item.gendermod.gender_transform_potion.effect.gender_transform", "变性药水");
        translationBuilder.add("item.gendermod.gender_transform_splash_potion.effect.gender_transform", "喷溅型变性药水");
        translationBuilder.add("item.gendermod.gender_transform_lingering_potion.effect.gender_transform", "滞留型变性药水");

        translationBuilder.add(GModStats.GENDER_TRANSITIONS.get(), "性转次数");
    }
}
