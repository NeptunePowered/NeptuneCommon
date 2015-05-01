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
package uk.jamierocks.neptune.common.mixin.util;

import net.canarymod.api.BoundingBox;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AxisAlignedBB.class)
public abstract class MixinAxisAlignedBB implements BoundingBox {

    @Shadow public double minX;
    @Shadow public double minY;
    @Shadow public double minZ;
    @Shadow public double maxX;
    @Shadow public double maxY;
    @Shadow public double maxZ;

    @Shadow public abstract AxisAlignedBB shadow$contract(double x, double y, double z);
    @Shadow public abstract AxisAlignedBB addCoord(double x, double y, double z);
    @Shadow public abstract AxisAlignedBB shadow$offset(double x, double y, double z);
    @Shadow public abstract AxisAlignedBB shadow$expand(double x, double y, double z);

    @Override
    public double getMinX() {
        return minX;
    }

    @Override
    public double getMinY() {
        return minY;
    }

    @Override
    public double getMinZ() {
        return minZ;
    }

    @Override
    public double getMaxX() {
        return maxX;
    }

    @Override
    public double getMaxY() {
        return maxY;
    }

    @Override
    public double getMaxZ() {
        return maxZ;
    }

    @Override
    public BoundingBox addCoordinates(double x, double y, double z) {
        return (BoundingBox) addCoord(x, y, z);
    }

    @Override
    public BoundingBox expand(double x, double y, double z) {
        return (BoundingBox) shadow$expand(x, y, z);
    }

    @Override
    public BoundingBox contract(double x, double y, double z) {
        return (BoundingBox) shadow$contract(x, y, z);
    }

    @Override
    public BoundingBox union(BoundingBox boundingBox) {
        return null;
    }

    @Override
    public BoundingBox offset(double x, double y, double z) {
        return (BoundingBox) shadow$offset(x, y, z);
    }

    @Override
    public double calculateXOffset(BoundingBox other, double xOffset) {
        return 0;
    }

    @Override
    public double calculateYOffset(BoundingBox other, double yOffset) {
        return 0;
    }

    @Override
    public double calculateZOffset(BoundingBox other, double zOffset) {
        return 0;
    }

    @Override
    public boolean intersectsWith(BoundingBox other) {
        return false;
    }

    @Shadow
    public abstract double getAverageEdgeLength();
}
