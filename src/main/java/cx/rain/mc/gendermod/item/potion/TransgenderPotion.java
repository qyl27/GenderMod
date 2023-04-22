package cx.rain.mc.gendermod.item.potion;

import cx.rain.mc.gendermod.GModConstants;
import cx.rain.mc.gendermod.capabilities.GModCapabilities;
import cx.rain.mc.gendermod.gender.GenderRegistry;
import cx.rain.mc.gendermod.stat.GModStats;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TransgenderPotion extends PotionItem {

    public TransgenderPotion(Properties arg) {
        super(arg);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!(livingEntity instanceof Player player)) {
            return stack;
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        // Todo: stat.
//        player.awardStat(GModStats.TRANSGENDER_TIMES);
        if (!player.getAbilities().instabuild) {
            var cap = player.getCapability(GModCapabilities.PLAYER_GENDER_CAPABILITY).orElseThrow(RuntimeException::new);
            var gender = cap.getGender();

            if (gender.getName().equals(GenderRegistry.MALE.get().getName())) {
                cap.setGender(GenderRegistry.FEMALE.get());
            } else if (gender.getName().equals(GenderRegistry.FEMALE.get().getName())) {
                cap.setGender(GenderRegistry.MALE.get());
            }

            stack.shrink(1);
        }

        if (!player.getAbilities().instabuild) {
            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
        }
        livingEntity.gameEvent(GameEvent.DRINK);

        stack.shrink(1);
        return stack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public ItemStack getDefaultInstance() {
        return new ItemStack(this);
    }

    @Override
    public String getDescriptionId() {
        return getOrCreateDescriptionId();
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> tooltipComponents, TooltipFlag isAdvanced) {
        tooltipComponents.add(Component.translatable(GModConstants.TRANSLATE_TRANSGENDER_POTION_EFFECT)
                .withStyle(ChatFormatting.BLUE));
    }
}