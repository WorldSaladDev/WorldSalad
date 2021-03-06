package com.radioctivetacoo.worldsalad.objects.items;

import java.util.List;

import com.radioctivetacoo.worldsalad.entities.Dragonfly;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class DragonflySaddleItem extends Item {

	public DragonflySaddleItem(Item.Properties builder) {
	      super(builder);
	   }

	   /**
	    * Returns true if the item can be used on the given entity, e.g. shears on sheep.
	    */
	   public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
	      if (target instanceof Dragonfly) {
	         Dragonfly dragonfly = (Dragonfly)target;
	         if (dragonfly.isAlive() && !dragonfly.getSaddled()) {
	            dragonfly.setSaddled(true);
	            dragonfly.world.playSound(playerIn, dragonfly.getPosX(), dragonfly.getPosY(), dragonfly.getPosZ(), SoundEvents.ENTITY_PIG_SADDLE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
	            stack.shrink(1);
	            return true;
	         }
	      }

	      return false;
	   }
	   
	   public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
			tooltip.add(new StringTextComponent("\u00A73" + "Saddle up and control a dragonfly."));
			tooltip.add(new StringTextComponent("\u00A78" + "\u00A7o" + "'Fly, dragon, fly!'"));
			super.addInformation(stack, worldIn, tooltip, flagIn);
		}
}
