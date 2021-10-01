package me.rochblondiaux.borringpackets.commons.netty.codecs;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import me.rochblondiaux.borringpackets.commons.netty.packets.FramedPacket;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 * <p>
 * Class responsible for grouped packet reception.
 */
public class GroupedPacketHandler extends MessageToByteEncoder<FramedPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, FramedPacket msg, ByteBuf out) {
    }

    @Override
    protected ByteBuf allocateBuffer(ChannelHandlerContext ctx, FramedPacket msg, boolean preferDirect) {
        return msg.getBody().retainedSlice();
    }

}