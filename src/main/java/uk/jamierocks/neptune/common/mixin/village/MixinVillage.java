/*
 * This file is part of NeptuneCommon, licensed under the MIT License (MIT).
 *
 * Copyright (c) Jamie Mansfield <https://github.com/jamierocks>
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
package uk.jamierocks.neptune.common.mixin.village;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.Village;
import net.canarymod.api.world.position.Location;
import net.minecraft.util.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(net.minecraft.village.Village.class)
public abstract class MixinVillage implements Village {

    @Shadow
    private BlockPos center;

    @Shadow
    private int noBreedTicks;

    @Shadow
    private int villageRadius;

    @Shadow
    private int numVillagers;

    @Shadow
    private int numIronGolems;

    @Override
    public void setReputationForPlayer(Player player, int rep) {

    }

    @Override
    public int getReputationForPlayer(Player player) {
        return 0;
    }

    @Override
    public boolean isPlayerReputationTooLow(Player player) {
        return false;
    }

    @Shadow
    public abstract boolean isMatingSeason();

    @Override
    public void startMatingSeason() {
        noBreedTicks -= 3600;
    }

    @Shadow
    public abstract void endMatingSeason();

    @Override
    public Location getCenter() {
        return new Location(center.getX(), center.getY(), center.getZ());
    }

    @Override
    public int getRadius() {
        return villageRadius;
    }

    @Override
    public int getVillagerCount() {
        return numVillagers;
    }

    @Override
    public int getIronGolemCount() {
        return numIronGolems;
    }

    @Shadow
    public abstract boolean isAnnihilated();
}
