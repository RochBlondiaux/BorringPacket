package me.rochblondiaux.borringpackets.commons.netty.codecs;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;
import me.rochblondiaux.borringpackets.commons.utils.PacketUtils;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class PacketEncoder<T extends BorringPacket> extends MessageToByteEncoder<T> {

    @Override
    protected void encode(ChannelHandlerContext ctx, T packet, ByteBuf buf) {
        PacketUtils.writePacket(buf, packet);
    }

}