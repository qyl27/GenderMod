package cx.rain.mc.gendermod.client.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonObjectModelPredicate extends CustomModelPredicate<JsonObject> {

    public JsonObjectModelPredicate(ModelPredicate<JsonObject> predicate) {
        super(predicate);
    }

    @Override
    public JsonObject parse(JsonElement json) {
        return json.getAsJsonObject();
    }
}
