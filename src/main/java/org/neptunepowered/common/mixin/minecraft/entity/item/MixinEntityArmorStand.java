/*
 * This file is part of NeptuneCommon, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2016, Jamie Mansfield <https://github.com/jamierocks>
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
package org.neptunepowered.common.mixin.minecraft.entity.item;

import net.canarymod.api.entity.ArmorStand;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.position.Rotations;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.item.ItemStack;
import org.neptunepowered.common.mixin.minecraft.entity.MixinEntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityArmorStand.class)
public abstract class MixinEntityArmorStand extends MixinEntityLivingBase implements ArmorStand {

    @Shadow
    public abstract ItemStack[] getInventory();

    @Shadow
    public abstract void setCurrentItemOrArmor(int slotIn, ItemStack stack);

    @Shadow
    public abstract boolean getShowArms();

    @Shadow
    public abstract boolean hasNoGravity();

    @Shadow
    public abstract void setNoGravity(boolean p_175425_1_);

    @Shadow
    public abstract boolean hasNoBasePlate();

    @Shadow
    public abstract void setNoBasePlate(boolean p_175426_1_);

    @Override
    public Item[] getAllEquipment() {
        return (Item[]) this.getInventory();
    }

    @Override
    public void setAllEquipment(Item[] item) {

    }

    @Override
    public Item getEquipment(Slot slot) {
        return this.getAllEquipment()[slot.ordinal()];
    }

    @Override
    public void setEquipment(Slot slot, Item item) {
        this.setCurrentItemOrArmor(slot.ordinal(), (ItemStack) item);
    }

    @Override
    @Shadow
    public abstract boolean isSmall();

    @Override
    @Shadow
    public abstract void setSmall(boolean small);

    @Override
    public boolean showArms() {
        return this.getShowArms();
    }

    @Override
    @Shadow
    public abstract void setShowArms(boolean set);

    @Override
    public boolean isSlotDiabled(Slot slot, Disability disability) {
        return false;
    }

    @Override
    public void disableSlot(Slot slot, Disability disability) {

    }

    @Override
    public void enableSlot(Slot slot, Disability disability) {

    }

    @Override
    public boolean hasBasePlate() {
        return this.hasNoBasePlate();
    }

    @Override
    public void setBasePlate(boolean basePlate) {
        this.setNoBasePlate(basePlate);
    }

    @Override
    public boolean gravity() {
        return this.hasNoGravity();
    }

    @Override
    public void setGravity(boolean gravity) {
        this.setNoGravity(gravity);
    }

    @Override
    public Rotations getPartPose(RotatablePart part) {
        return null;
    }

    @Override
    public void setPartPose(RotatablePart part, Rotations rotation) {

    }

    @Override
    public String getFqName() {
        return "ArmorStand";
    }

    @Override
    public EntityType getEntityType() {
        return EntityType.ARMORSTAND;
    }
}
