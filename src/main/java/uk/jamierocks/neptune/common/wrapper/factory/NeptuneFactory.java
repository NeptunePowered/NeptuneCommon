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
package uk.jamierocks.neptune.common.wrapper.factory;

import net.canarymod.api.factory.AIFactory;
import net.canarymod.api.factory.AttributeFactory;
import net.canarymod.api.factory.ChatComponentFactory;
import net.canarymod.api.factory.EntityFactory;
import net.canarymod.api.factory.Factory;
import net.canarymod.api.factory.ItemFactory;
import net.canarymod.api.factory.NBTFactory;
import net.canarymod.api.factory.ObjectFactory;
import net.canarymod.api.factory.PacketFactory;
import net.canarymod.api.factory.PotionFactory;
import net.canarymod.api.factory.StatisticsFactory;

public class NeptuneFactory implements Factory {

    private final PotionFactory potionFactory = new NeptunePotionFactory();
    private final NBTFactory nbtFactory = new NeptuneNBTFactory();
    private final StatisticsFactory statisticsFactory = new NeptuneStatisticsFactory();

    @Override
    public ItemFactory getItemFactory() {
        return null;
    }

    @Override
    public PotionFactory getPotionFactory() {
        return potionFactory;
    }

    @Override
    public EntityFactory getEntityFactory() {
        return null;
    }

    @Override
    public ObjectFactory getObjectFactory() {
        return null;
    }

    @Override
    public NBTFactory getNBTFactory() {
        return nbtFactory;
    }

    @Override
    public PacketFactory getPacketFactory() {
        return null;
    }

    @Override
    public ChatComponentFactory getChatComponentFactory() {
        return null;
    }

    @Override
    public AttributeFactory getAttributeFactory() {
        return null;
    }

    @Override
    public StatisticsFactory getStatisticsFactory() {
        return statisticsFactory;
    }

    @Override
    public AIFactory getAIFactory() {
        return null;
    }
}
