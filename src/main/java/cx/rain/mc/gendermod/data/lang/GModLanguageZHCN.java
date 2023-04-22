package cx.rain.mc.gendermod.data.lang;

import cx.rain.mc.gendermod.GModConstants;
import cx.rain.mc.gendermod.item.GModItems;
import net.minecraft.data.PackOutput;

public class GModLanguageZHCN extends LanguageProviderBase {

    public GModLanguageZHCN(PackOutput output, String modid, String locale) {
        super(output, modid, locale);
    }

    @Override
    protected void addTranslations() {
        addItem(GModItems.TRANSGENDER_POTION, "变性药水");
        add(GModConstants.TRANSLATE_EFFECT_TRANSGENDER_POTION_NAME, "改变性别");
        add(GModConstants.TRANSLATE_ITEM_TRANSGENDER_POTION_DESC, "改变使用者的性别，只能使用一次");
        add(GModConstants.TRANSLATE_ITEM_TRANSGENDER_POTION_NAME, "变性药水");
        add(GModConstants.TRANSLATE_STAT_GENDER_TRANSITIONS, "性转次数");
    }
}
