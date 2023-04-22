package cx.rain.mc.gendermod.data;

import cx.rain.mc.gendermod.item.GModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GModItemModelProvider extends ItemModelProvider {

    /// <editor-fold desc="Util codes.">

    public static final ResourceLocation GENERATED = new ResourceLocation("minecraft", "item/generated");
    public static final ResourceLocation HANDHELD = new ResourceLocation("minecraft", "item/handheld");

    public GModItemModelProvider(PackOutput output, String modid, ExistingFileHelper helper) {
        super(output, modid, helper);
    }

    protected ResourceLocation blockLoc(ResourceLocation path) {
        return new ResourceLocation(path.getNamespace(), BLOCK_FOLDER + "/" + path.getPath());
    }

    protected ResourceLocation foldedLoc(ResourceLocation path) {
        return path.getPath().contains("/") ? path :
                new ResourceLocation(path.getNamespace(), folder + "/" + path.getPath());
    }

    protected ItemModelBuilder withBlockParent(Block block) {
        var name = ForgeRegistries.BLOCKS.getKey(block);
        return withExistingParent(name.getPath(), blockLoc(name));
    }

    protected ItemModelBuilder withBlockParent(RegistryObject<Block> block) {
        var name = block.getId();
        return withExistingParent(name.getPath(), blockLoc(name));
    }

    protected void generated(ItemLike item) {
        String path = ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
        withExistingParent(path, GENERATED).texture("layer0", modLoc("item/" + path));
    }

    protected void handheld(ItemLike item) {
        String path = ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
        withExistingParent(path, HANDHELD).texture("layer0", modLoc("item/" + path));
    }

    protected void handheld(Block block) {
        String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
        withExistingParent(path, HANDHELD).texture("layer0", modLoc("block/" + path));
    }

    protected void blockItem(Block block) {
        String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
        withExistingParent(path, modLoc("block/" + path));
    }

    protected void blockItem(Block block, String statedModel) {
        String path = ForgeRegistries.BLOCKS.getKey(block).getPath();
        withExistingParent(path, modLoc("block/" + path + "_" + statedModel));
    }

    /// </editor-fold>

    @Override
    protected void registerModels() {
        basicItem(GModItems.TRANSGENDER_POTION.get());
    }
}
