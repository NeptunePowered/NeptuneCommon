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
package org.neptunepowered.common.wrapper.commandsys;

import net.canarymod.chat.MessageReceiver;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.neptunepowered.common.util.Wrapper;
import org.neptunepowered.common.wrapper.chat.NeptuneChatComponent;

public class WrapperICommandSender extends Wrapper<MessageReceiver> implements ICommandSender {

    public WrapperICommandSender(MessageReceiver handle) {
        super(handle);
    }

    @Override
    public String getCommandSenderName() {
        return getHandle().getName();
    }

    @Override
    public IChatComponent getDisplayName() {
        return new ChatComponentText(getHandle().getName());
    }

    @Override
    public void addChatMessage(IChatComponent component) {
        getHandle().message(new NeptuneChatComponent(component));
    }

    @Override
    public boolean canCommandSenderUseCommand(int permLevel, String commandName) {
        return getHandle().hasPermission(commandName);
    }

    @Override
    public BlockPos getPosition() {
        return BlockPos.ORIGIN;
    }

    @Override
    public Vec3 getPositionVector() {
        return new Vec3(0, 0, 0);
    }

    @Override
    public World getEntityWorld() {
        return null;
    }

    @Override
    public Entity getCommandSenderEntity() {
        return null;
    }

    @Override
    public boolean sendCommandFeedback() {
        return true;
    }

    @Override
    public void setCommandStat(CommandResultStats.Type type, int amount) {

    }
}
