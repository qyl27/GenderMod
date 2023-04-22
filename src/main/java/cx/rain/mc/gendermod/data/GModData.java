package cx.rain.mc.gendermod.data;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.data.lang.GModLanguageENUS;
import cx.rain.mc.gendermod.data.lang.GModLanguageZHCN;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GenderMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GModData {
    @SubscribeEvent
    public static void onRegisterPredicates(GatherDataEvent event) {
        var gen = event.getGenerator();
        var output = gen.getPackOutput();
        var exHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            gen.addProvider(true, new GModBlockStateProvider(output, GenderMod.MODID, exHelper));
            gen.addProvider(true, new GModItemModelProvider(output, GenderMod.MODID, exHelper));
        }

        if (event.includeServer()) {
            gen.addProvider(true, new GModLanguageZHCN(output, GenderMod.MODID, "zh_cn"));
            gen.addProvider(true, new GModLanguageENUS(output, GenderMod.MODID, "en_us"));
        }
    }
}
