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
package org.neptunepowered.common.mixin.server.management;

import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import net.canarymod.Canary;
import net.canarymod.api.ConfigurationManager;
import net.canarymod.api.chat.ChatComponent;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.World;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.player.ConnectionHook;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Iterator;
import java.util.List;

@Mixin(ServerConfigurationManager.class)
public abstract class MixinServerConfigurationManager implements ConfigurationManager {

    @Shadow private Logger logger;
    @Shadow private MinecraftServer mcServer;

    @Shadow
    public abstract NBTTagCompound readPlayerDataFromFile(EntityPlayerMP playerIn);

    @Shadow
    protected abstract void setPlayerGameTypeBasedOnOther(EntityPlayerMP p_72381_1_, EntityPlayerMP p_72381_2_,
            net.minecraft.world.World worldIn);

    @Shadow
    protected abstract void func_96456_a(ServerScoreboard scoreboardIn, EntityPlayerMP playerIn);

    @Shadow
    public abstract void sendChatMsg(IChatComponent component);

    @Shadow
    public abstract void playerLoggedIn(EntityPlayerMP playerIn);

    @Shadow
    public abstract void updateTimeAndWeatherForPlayer(EntityPlayerMP playerIn, WorldServer worldIn);

    @Overwrite
    public void initializeConnectionToPlayer(NetworkManager netManager, EntityPlayerMP playerIn) {
        GameProfile gameprofile = playerIn.getGameProfile();
        PlayerProfileCache playerprofilecache = this.mcServer.getPlayerProfileCache();
        GameProfile gameprofile1 = playerprofilecache.getProfileByUUID(gameprofile.getId());
        String s = gameprofile1 == null ? gameprofile.getName() : gameprofile1.getName();
        playerprofilecache.addEntry(gameprofile);
        NBTTagCompound nbttagcompound = this.readPlayerDataFromFile(playerIn);
        playerIn.setWorld(this.mcServer.worldServerForDimension(playerIn.dimension));
        playerIn.theItemInWorldManager.setWorld((WorldServer) playerIn.worldObj);
        String s1 = "local";

        if (netManager.getRemoteAddress() != null) {
            s1 = netManager.getRemoteAddress().toString();
        }

        logger.info(playerIn.getCommandSenderName() + "[" + s1 + "] logged in with entity id " + playerIn.getEntityId()
                + " at (" + playerIn.posX + ", " + playerIn.posY + ", " + playerIn.posZ + ")");
        WorldServer worldserver = this.mcServer.worldServerForDimension(playerIn.dimension);
        WorldInfo worldinfo = worldserver.getWorldInfo();
        BlockPos blockpos = worldserver.getSpawnPoint();
        this.setPlayerGameTypeBasedOnOther(playerIn, null, worldserver);
        NetHandlerPlayServer nethandlerplayserver = new NetHandlerPlayServer(this.mcServer, netManager, playerIn);
        nethandlerplayserver.sendPacket(
                new S01PacketJoinGame(playerIn.getEntityId(), playerIn.theItemInWorldManager.getGameType(),
                        worldinfo.isHardcoreModeEnabled(), worldserver.provider.getDimensionId(),
                        worldserver.getDifficulty(), this.getMaxPlayers(), worldinfo.getTerrainType(),
                        worldserver.getGameRules().getGameRuleBooleanValue("reducedDebugInfo")));
        nethandlerplayserver
                .sendPacket(new S3FPacketCustomPayload("MC|Brand", (new PacketBuffer(Unpooled.buffer())).writeString(
                        this.mcServer.getServerModName())));
        nethandlerplayserver
                .sendPacket(new S41PacketServerDifficulty(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        nethandlerplayserver.sendPacket(new S05PacketSpawnPosition(blockpos));
        nethandlerplayserver.sendPacket(new S39PacketPlayerAbilities(playerIn.capabilities));
        nethandlerplayserver.sendPacket(new S09PacketHeldItemChange(playerIn.inventory.currentItem));
        playerIn.getStatFile().func_150877_d();
        playerIn.getStatFile().func_150884_b(playerIn);
        this.func_96456_a((ServerScoreboard) worldserver.getScoreboard(), playerIn);
        this.mcServer.refreshStatusNextTick();
        ChatComponentTranslation chatcomponenttranslation;

        if (!playerIn.getCommandSenderName().equalsIgnoreCase(s)) {
            chatcomponenttranslation = new ChatComponentTranslation("multiplayer.player.joined.renamed",
                    new Object[]{playerIn.getDisplayName(), s});
        } else {
            chatcomponenttranslation =
                    new ChatComponentTranslation("multiplayer.player.joined", new Object[]{playerIn.getDisplayName()});
        }

        chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.YELLOW);

        // Neptune start
        ConnectionHook hook = (ConnectionHook)
                new ConnectionHook(
                        (Player) playerIn, chatcomponenttranslation.getFormattedText(), false
                ).call();
        if (!hook.isHidden()) {
            this.sendChatMsg(chatcomponenttranslation);
        }
        // Neptune end
        //this.sendChatMsg(chatcomponenttranslation); // Called above

        this.playerLoggedIn(playerIn);
        nethandlerplayserver.setPlayerLocation(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw,
                playerIn.rotationPitch);
        this.updateTimeAndWeatherForPlayer(playerIn, worldserver);

        if (this.mcServer.getResourcePackUrl().length() > 0) {
            playerIn.loadResourcePack(this.mcServer.getResourcePackUrl(), this.mcServer.getResourcePackHash());
        }

        Iterator iterator = playerIn.getActivePotionEffects().iterator();

        while (iterator.hasNext()) {
            PotionEffect potioneffect = (PotionEffect) iterator.next();
            nethandlerplayserver.sendPacket(new S1DPacketEntityEffect(playerIn.getEntityId(), potioneffect));
        }

        playerIn.addSelfToInternalCraftingInventory();

        if (nbttagcompound != null && nbttagcompound.hasKey("Riding", 10)) {
            Entity entity = EntityList.createEntityFromNBT(nbttagcompound.getCompoundTag("Riding"), worldserver);

            if (entity != null) {
                entity.forceSpawn = true;
                worldserver.spawnEntityInWorld(entity);
                playerIn.mountEntity(entity);
                entity.forceSpawn = false;
            }
        }

        // Neptune start
        Canary.motd().sendMOTD((MessageReceiver) playerIn);
        // Neptune end
    }

    @Shadow
    public abstract int getCurrentPlayerCount();

    @Override
    public void sendPacketToAllInWorld(String world, Packet packet) {

    }

    @Override
    public int getNumPlayersOnline() {
        return getCurrentPlayerCount();
    }

    @Override
    public Player getPlayerByName(String name) {
        return null;
    }

    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    @Shadow
    public abstract int getMaxPlayers();

    @Override
    public void markBlockNeedsUpdate(int x, int y, int z, DimensionType dimension, String world) {

    }

    @Override
    public void switchDimension(Player player, World world, boolean createPortal) {

    }
}