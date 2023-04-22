package cx.rain.mc.gendermod.item;

import cx.rain.mc.gendermod.GenderMod;
import cx.rain.mc.gendermod.item.potion.TransgenderPotion;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GenderMod.MODID);

    public static final RegistryObject<Item> TRANSGENDER_POTION = ITEMS.register("transgender_potion", () -> new TransgenderPotion(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
