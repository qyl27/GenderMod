package cx.rain.mc.gendermod.forge.event;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.core.gender.GenderManager;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mod.EventBusSubscriber(modid = GenderMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ReloadListener {
    @SubscribeEvent
    public static void onReload(AddReloadListenerEvent event) {
        event.addListener(new Listener());
    }

    public static class Listener implements PreparableReloadListener {
        @Override
        public CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager,
                                              ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler,
                                              Executor backgroundExecutor, Executor gameExecutor) {
            return CompletableFuture.runAsync(() -> GenderManager.onReload(resourceManager));
        }
    }
}
