package cx.rain.mc.gendermod.item.potion;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;

public class GenderTransLingeringPotionItem extends GenderTransThrowablePotionItem {
    public GenderTransLingeringPotionItem() {
        super();
    }

    @Override
    public ItemStack getPotion() {
        return PotionUtils.setPotion(new ItemStack(Items.LINGERING_POTION), GModPotions.GENDER_TRANSFORM.get());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.LINGERING_POTION_THROW, SoundSource.NEUTRAL, 0.5f, 0.4f / (level.getRandom().nextFloat() * 0.4f + 0.8f));
        return super.use(level, player, usedHand);
    }
}
