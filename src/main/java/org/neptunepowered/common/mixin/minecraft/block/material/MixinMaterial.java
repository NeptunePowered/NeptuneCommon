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
package org.neptunepowered.common.mixin.minecraft.block.material;

import net.canarymod.api.world.blocks.BlockMaterial;
import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Material.class)
public abstract class MixinMaterial implements BlockMaterial {

    @Shadow private boolean canBurn;
    @Shadow private int mobilityFlag;
    @Shadow private boolean isTranslucent;
    @Shadow private boolean requiresNoTool;

    @Shadow
    public abstract boolean blocksLight();

    @Override
    @Shadow
    public abstract boolean isLiquid();

    @Override
    @Shadow
    public abstract boolean isSolid();

    @Override
    public boolean canPreventGrassGrowth() {
        return this.blocksLight();
    }

    @Override
    public boolean canBurn() {
        return this.canBurn;
    }

    @Override
    @Shadow
    public abstract boolean isReplaceable();

    @Override
    @Shadow
    public abstract boolean isOpaque();

    @Override
    public boolean noToolRequired() {
        return this.requiresNoTool;
    }

    @Override
    public int getMobility() {
        return this.mobilityFlag;
    }

    @Override
    public boolean isAlwaysHarvested() {
        return false;
    }

    @Override
    public boolean isTranslucent() {
        return this.isTranslucent;
    }
}
