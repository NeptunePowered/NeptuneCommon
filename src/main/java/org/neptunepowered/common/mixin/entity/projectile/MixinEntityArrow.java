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
package org.neptunepowered.common.mixin.entity.projectile;

import net.canarymod.api.entity.Arrow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityArrow.class)
@Implements(@Interface(iface = Arrow.class, prefix = "arrow$"))
public abstract class MixinEntityArrow extends Entity {

    @Shadow public Entity shootingEntity;
    @Shadow public int canBePickedUp;
    @Shadow private boolean inGround;
    @Shadow private int knockbackStrength;
    @Shadow private int ticksInGround;
    @Shadow private int ticksInAir;
    @Shadow private double damage;

    public MixinEntityArrow(World worldIn) {
        super(worldIn);
    }

    @Shadow
    public abstract boolean getIsCritical();

    @Shadow
    public abstract void setIsCritical(boolean critical);

    public boolean arrow$canPickUp() {
        return canBePickedUp == 1 ? true : false;
    }

    public void arrow$setCanPickUp(boolean canPickUp) {
        this.canBePickedUp = canPickUp == true ? 1 : 0;
    }

    public double arrow$getDamage() {
        return damage;
    }

    public void arrow$setDamage(double damage) {
        this.damage = damage;
    }

    public boolean arrow$isCritical() {
        return getIsCritical();
    }

    public void arrow$setIsCritical(boolean critical) {
        setIsCritical(critical);
    }

    public net.canarymod.api.entity.Entity arrow$getOwner() {
        return (net.canarymod.api.entity.Entity) shootingEntity;
    }

    public boolean arrow$isInGround() {
        return inGround;
    }

    public int arrow$getTicksInAir() {
        return ticksInAir;
    }

    public int arrow$getTicksInGround() {
        return ticksInGround;
    }

    public int arrow$getKnockbackStrength() {
        return knockbackStrength;
    }

    public void arrow$setKnockbackStrenth(int knockback) {
        this.knockbackStrength = knockback;
    }
}
