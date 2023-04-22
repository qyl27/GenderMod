package cx.rain.mc.gendermod.data.lang;

import cx.rain.mc.gendermod.GModConstants;
import cx.rain.mc.gendermod.item.GModItems;
import net.minecraft.data.PackOutput;

public class GModLanguageENUS extends LanguageProviderBase {

    public GModLanguageENUS(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        addItem(GModItems.TRANSGENDER_POTION, "Gender transition potion");
        add(GModConstants.TRANSLATE_EFFECT_TRANSGENDER_POTION_NAME, "Change gender");
        add(GModConstants.TRANSLATE_ITEM_TRANSGENDER_POTION_DESC, "Change gender of user, only can be used once");
        add(GModConstants.TRANSLATE_ITEM_TRANSGENDER_POTION_NAME, "Gender transition potion");
        add(GModConstants.TRANSLATE_STAT_GENDER_TRANSITIONS, "Gender transition times");
    }
}
