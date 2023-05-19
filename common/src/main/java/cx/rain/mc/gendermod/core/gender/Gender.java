package cx.rain.mc.gendermod.core.gender;

import net.minecraft.resources.ResourceLocation;

public class Gender {
    private ResourceLocation id;

    public Gender(ResourceLocation id) {
        this.id = id;
    }

    public ResourceLocation getId() {
        return id;
    }

    
}
