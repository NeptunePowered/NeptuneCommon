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
package org.neptunepowered.common.mixin.server.network;

import net.canarymod.hook.system.ServerListPingHook;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.NetHandlerStatusServer;
import org.neptunepowered.common.wrapper.chat.NeptuneChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.net.InetSocketAddress;

@Mixin(NetHandlerStatusServer.class)
public class MixinNetHandlerStatusServer {

    @Shadow private MinecraftServer server;
    @Shadow private NetworkManager networkManager;

    @Inject(method = "processServerQuery", at = @At("HEAD"))
    public void onProcessServerQuery(C00PacketServerQuery packetIn) {
        ServerListPingHook serverListPingHook =
                (ServerListPingHook) new ServerListPingHook((InetSocketAddress) networkManager.getRemoteAddress(), 0,
                        null, 0, new NeptuneChatComponent(server.getServerStatusResponse().getServerDescription()),
                        0, 0, server.getServerStatusResponse().getFavicon(), null).call();
        if (serverListPingHook.isCanceled()) {
            return;
        }
    }
}
