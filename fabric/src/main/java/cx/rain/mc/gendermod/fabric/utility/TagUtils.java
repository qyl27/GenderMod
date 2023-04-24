package cx.rain.mc.gendermod.fabric.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.minecraft.nbt.*;

import java.util.regex.Pattern;

public class TagUtils {
    /**
     * Check if source matches json object.
     * @param source Source compound tag.
     * @param match Match object.
     * @return Result.
     */
    public static boolean matchesCompound(CompoundTag source, JsonObject match) {
        for (var entry : match.entrySet()) {
            String key = entry.getKey();
            JsonElement element = entry.getValue();
            if (element.isJsonNull()) {
                if (source.contains(key)) {
                    return false;
                }
            } else {
                if (!source.contains(key) || !matchesTag(source.get(key), element)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if source matches json element.
     * @param source Source tag.
     * @param match Match element.
     * @return Result.
     */
    protected static boolean matchesTag(Tag source, JsonElement match) {
        if (match.isJsonObject()) {
            return source instanceof CompoundTag && matchesCompound((CompoundTag) source, match.getAsJsonObject());
        } else if (match.isJsonArray()) {
            return source instanceof CollectionTag && matchesCollection((CollectionTag<?>) source, match.getAsJsonArray());
        } else {
            JsonPrimitive primitive = match.getAsJsonPrimitive();
            if (source instanceof NumericTag numericTag) {
                boolean isInteger = source instanceof ByteTag
                        || source instanceof ShortTag
                        || source instanceof IntTag
                        || source instanceof LongTag;

                if (primitive.isBoolean()) {
                    return isInteger && primitive.getAsBoolean() == (numericTag.getAsInt() == 1);
                } else if (primitive.isNumber()) {
                    if (isInteger) {
                        return primitive.getAsLong() == numericTag.getAsLong();
                    } else {
                        return primitive.getAsDouble() == numericTag.getAsDouble();
                    }
                } else if (primitive.isString()) {
                    return false;
                }
            } else if (source instanceof StringTag) {
                if (primitive.isString()) {
                    String prim = primitive.getAsString();
                    String text = source.getAsString();
                    if (prim.startsWith("/") && prim.endsWith("/")) {
                        return Pattern.matches(prim.substring(1, prim.length() - 1), text);
                    } else {
                        return prim.equals(text);
                    }
                }
            }
        }
        return false;
    }

    /**
     * Check if source contains all elements of match array.
     * @param source Source list.
     * @param match Match array.
     * @return Result.
     */
    protected static boolean matchesCollection(CollectionTag<?> source, JsonArray match) {
        if (source.size() < match.size()) {
            return false;
        }

        for (JsonElement element : match) {
            for (Object object : source) {
                if (object instanceof Tag tag) {
                    if (matchesTag(tag, element)) {
                        break;
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
