package cx.rain.mc.gendermod.core.gender;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.HashMap;
import java.util.Map;

public class GenderManager {
    private static final Gson GSON = new Gson();

    private static final Map<ResourceLocation, Gender> GENDER_REGISTRY = new HashMap<>();

    public static void onReload(ResourceManager manager) {
        for (var entry : manager.listResources("genders", path -> path.getPath().endsWith(".json")).entrySet()) {
            try (var reader = entry.getValue().openAsReader()) {
                var json = GSON.fromJson(reader, JsonElement.class).getAsJsonObject();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
