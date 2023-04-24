package cx.rain.mc.gendermod.item.potion;

import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class GenderTransThrowablePotionItem extends GenderTransPotionItem {
    public GenderTransThrowablePotionItem() {
        super();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        PotionUtils.addPotionTooltip(stack, tooltipComponents, 0.25f);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack itemStack = player.getItemInHand(usedHand);
        if (!level.isClientSide) {
            ThrownPotion thrownPotion = new ThrownPotion(level, player);
            thrownPotion.setItem(PotionUtils.setPotion(new ItemStack(Items.SPLASH_POTION), GModPotion.GENDER_TRANSFORM.get()));
            thrownPotion.shootFromRotation(player, player.getXRot(), player.getYRot(), -20.0f, 0.5f, 1.0f);
            level.addFreshEntity(thrownPotion);
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        if (!player.getAbilities().instabuild) {
            itemStack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }

    public abstract ItemStack getPotion();
}
