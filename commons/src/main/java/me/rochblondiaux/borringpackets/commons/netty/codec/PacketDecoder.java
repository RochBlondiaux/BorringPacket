package me.rochblondiaux.borringpackets.commons.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.rochblondiaux.borringpackets.commons.netty.packets.InboundPacket;
import me.rochblondiaux.borringpackets.commons.utils.Utils;

import java.util.List;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 * <p>
 * Class responsible for packet decoding.
 */
public class PacketDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list) {
        if (buf.readableBytes() <= 0) return;
        final int packetId = Utils.readVarInt(buf);
        list.add(new InboundPacket(packetId, buf));
    }
}