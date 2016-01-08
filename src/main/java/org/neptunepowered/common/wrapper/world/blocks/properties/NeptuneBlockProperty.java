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
package org.neptunepowered.common.wrapper.world.blocks.properties;

import net.canarymod.api.world.blocks.properties.BlockProperty;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import org.neptunepowered.common.util.Wrapper;

import java.util.Collection;

public class NeptuneBlockProperty extends Wrapper<IProperty> implements BlockProperty {

    public NeptuneBlockProperty(IProperty handle) {
        super(handle);
    }

    @Override
    public String getName() {
        return this.getHandle().getName();
    }

    @Override
    public Collection<Comparable> getAllowedValues() {
        return this.getHandle().getAllowedValues();
    }

    @Override
    public Class getValueClass() {
        return this.getHandle().getValueClass();
    }

    @Override
    public boolean canApply(Comparable value) {
        return this.getAllowedValues().contains(value);
    }

    public static BlockProperty of(IProperty property) {
        if (property instanceof PropertyBool) {
            return new NeptuneBlockBooleanProperty((PropertyBool) property);
        } else if (property instanceof PropertyDirection) {
            return new NeptuneBlockDirectionProperty((PropertyDirection) property);
        } else if (property instanceof PropertyEnum) {
            return new NeptuneBlockEnumProperty((PropertyEnum) property);
        } else if (property instanceof PropertyInteger) {
            return new NeptuneBlockIntegerProperty((PropertyInteger) property);
        }
        return new NeptuneBlockProperty(property);
    }
}
