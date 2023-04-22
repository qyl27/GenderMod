package cx.rain.mc.gendermod.data.lang;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.LanguageProvider;

public abstract class LanguageProviderBase extends LanguageProvider {
    protected String modid;
    protected String locale;

    public LanguageProviderBase(PackOutput output, String modid, String locale) {
        super(output, modid, locale);

        this.modid = modid;
        this.locale = locale;
    }

    protected void addTab(ResourceLocation name, String translate) {
        add("tab." + modid + "." + name.getPath(), translate);
    }
}
