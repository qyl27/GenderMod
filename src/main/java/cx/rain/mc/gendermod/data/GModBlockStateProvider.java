package cx.rain.mc.gendermod.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class GModBlockStateProvider extends BlockStateProvider {

    /// <editor-fold desc="Util codes.">

    protected final String modid;

    public GModBlockStateProvider(PackOutput output, String modid, ExistingFileHelper exFileHelper) {
        super(output, modid, exFileHelper);
        this.modid = modid;
    }

    protected ResourceLocation blockLoc(ResourceLocation path) {
        return new ResourceLocation(path.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + path.getPath());
    }

    /// </editor-fold>

    @Override
    protected void registerStatesAndModels() {

    }
}
