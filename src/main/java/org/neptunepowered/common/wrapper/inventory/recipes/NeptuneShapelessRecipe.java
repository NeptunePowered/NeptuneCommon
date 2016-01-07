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
package org.neptunepowered.common.wrapper.inventory.recipes;

import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.recipes.ShapelessRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import org.neptunepowered.common.interfaces.minecraft.item.crafting.IMixinShapelessRecipes;

import java.util.List;

public class NeptuneShapelessRecipe extends NeptuneRecipe implements ShapelessRecipe {

    public NeptuneShapelessRecipe(ShapelessRecipes handle) {
        super(handle);
    }

    @Override
    public List<Item> getRecipeItems() {
        return ((IMixinShapelessRecipes) this.getHandle()).getRecipeItems();
    }

    @Override
    public boolean isShapeless() {
        return true;
    }

    @Override
    public boolean isShaped() {
        return false;
    }

    @Override
    public ShapelessRecipes getHandle() {
        return (ShapelessRecipes) super.getHandle();
    }
}
