package me.rochblondiaux.borringpackets.commons.netty.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.CorruptedFrameException;
import me.rochblondiaux.borringpackets.commons.utils.PacketUtils;
import me.rochblondiaux.borringpackets.commons.utils.Utils;

import java.util.List;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class PacketFramer extends ByteToMessageCodec<ByteBuf> {

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf from, ByteBuf to) throws Exception {
        PacketUtils.frameBuffer(from, to);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
        buf.markReaderIndex();

        for (int i = 0; i < 3; ++i) {
            if (!buf.isReadable()) {
                buf.resetReaderIndex();
                return;
            }

            final byte b = buf.readByte();

            if (b >= 0) {
                buf.resetReaderIndex();

                final int packetSize = Utils.readVarInt(buf);

                // Max packet size check
                if (packetSize >= 30000) {
                    ctx.close();
                    throw new IllegalStateException(String.format("Server sent a packet over the maximum size (%d)", packetSize));
                }

                if (buf.readableBytes() < packetSize) {
                    buf.resetReaderIndex();
                    return;
                }

                out.add(buf.readRetainedSlice(packetSize));
                return;
            }
        }

        throw new CorruptedFrameException("length wider than 21-bit");
    }
}
