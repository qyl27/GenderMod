package cx.rain.mc.gendermod.forge.event;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.core.gender.GenderManager;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GenderMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ReloadListener {
    @SubscribeEvent
    public static void onReload(AddReloadListenerEvent event) {
        event.addListener(new GenderManager());
    }
}
