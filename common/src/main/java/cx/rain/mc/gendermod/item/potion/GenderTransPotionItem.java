package cx.rain.mc.gendermod.item.potion;

import cx.rain.mc.gendermod.effect.GModEffects;
import cx.rain.mc.gendermod.mixins.interfaces.IGenderHolder;
import cx.rain.mc.gendermod.stat.GModStats;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class GenderTransPotionItem extends PotionItem {

    public GenderTransPotionItem() {
        super(new Properties()
                .stacksTo(1)
                .arch$tab(CreativeModeTabs.FOOD_AND_DRINKS));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        var holder = (IGenderHolder) player;
        if (!holder.hasTransformedGender()) {
            return super.use(level, player, usedHand);
        }

        return InteractionResultHolder.pass(player.getItemInHand(usedHand));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        Player player = livingEntity instanceof Player ? (Player)livingEntity : null;
        if (player instanceof ServerPlayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, stack);
        }

        if (player == null) {
            return stack;
        }

        player.awardStat(Stats.ITEM_USED.get(this));
        player.awardStat(Stats.CUSTOM.get(GModStats.GENDER_TRANSITIONS.get()));

        if (!level.isClientSide) {
            GModEffects.GENDER_TRANSFORM.get().applyInstantenousEffect(player, player, livingEntity, 0, 0);
        }

        if (!player.getAbilities().instabuild) {
            stack.shrink(1);

            if (stack.isEmpty()) {
                return new ItemStack(Items.GLASS_BOTTLE);
            }

            player.getInventory().add(new ItemStack(Items.GLASS_BOTTLE));
        }
        livingEntity.gameEvent(GameEvent.DRINK);

        return stack;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public ItemStack getDefaultInstance() {
        var stack = new ItemStack(this);

        if (PotionUtils.getPotion(stack) != GModPotions.GENDER_TRANSFORM.get()) {
            PotionUtils.setPotion(stack, GModPotions.GENDER_TRANSFORM.get());
        }

        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (PotionUtils.getPotion(stack) != GModPotions.GENDER_TRANSFORM.get()) {
            PotionUtils.setPotion(stack, GModPotions.GENDER_TRANSFORM.get());
        }
    }

    @Override
    public String getDescriptionId(ItemStack stack) {
        return this.getDescriptionId();
    }
}
