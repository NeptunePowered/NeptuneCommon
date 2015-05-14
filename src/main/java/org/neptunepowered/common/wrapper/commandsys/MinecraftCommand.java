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

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.DynamicCommandAnnotation;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;

public class MinecraftCommand extends CanaryCommand {

    private final ICommand command;

    public MinecraftCommand(ICommand command) {
        super(new DynamicCommandAnnotation(
                (String[]) command.getCommandAliases().toArray(new String[command.getCommandAliases().size()]),
                new String[0], "", "", "", "", new String[0], 0, 0, "", 0), null, null);
        this.command = command;
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        try {
            command.processCommand(new WrapperICommandSender(caller), parameters);
        } catch (CommandException e) {
            Canary.log.error("Eh, something has broken :(", e);
        }
    }
}
