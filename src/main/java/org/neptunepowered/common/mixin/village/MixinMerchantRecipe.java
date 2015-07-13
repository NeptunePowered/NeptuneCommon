/*
 * This file is part of NeptuneCommon, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015, Jamie Mansfield <https://github.com/jamierocks>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.neptunepowered.common.mixin.village;

import net.canarymod.api.VillagerTrade;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.CompoundTag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MerchantRecipe.class)
public abstract class MixinMerchantRecipe implements VillagerTrade {

    @Shadow private ItemStack itemToBuy;
    @Shadow private ItemStack secondItemToBuy;
    @Shadow private ItemStack itemToSell;
    @Shadow private int maxTradeUses;

    @Shadow
    public abstract NBTTagCompound writeToTags();

    @Shadow
    public abstract void readFromTags(NBTTagCompound p_77390_1_);

    @Shadow
    public abstract boolean isRecipeDisabled();

    @Override
    public Item getBuyingOne() {
        return (Item) itemToBuy;
    }

    @Override
    public void setBuyingOne(Item item) {
        itemToBuy = (ItemStack) item;
    }

    @Override
    public Item getBuyingTwo() {
        return (Item) secondItemToBuy;
    }

    @Override
    public void setBuyingTwo(Item item) {
        secondItemToBuy = (ItemStack) item;
    }

    @Override
    public boolean requiresTwoItems() {
        return secondItemToBuy != null;
    }

    @Override
    public Item getSelling() {
        return (Item) itemToSell;
    }

    @Override
    public void setSelling(Item item) {
        itemToSell = (ItemStack) item;
    }

    @Override
    public void use() {
        // TODO: Something fancy goes here.
    }

    @Override
    public void increaseMaxUses(int increase) {
        maxTradeUses += increase;
    }

    @Override
    public boolean isUsedUp() {
        return isRecipeDisabled();
    }

    @Override
    public CompoundTag getDataAsTag() {
        return (CompoundTag) writeToTags();
    }

    @Override
    public void readFromTag(CompoundTag tag) {
        readFromTags((NBTTagCompound) tag);
    }
}
