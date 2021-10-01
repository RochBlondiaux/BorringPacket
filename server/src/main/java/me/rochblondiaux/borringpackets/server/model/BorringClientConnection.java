package me.rochblondiaux.borringpackets.server.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.SocketChannel;
import lombok.NonNull;
import me.rochblondiaux.borringpackets.commons.model.client.ClientPacket;
import me.rochblondiaux.borringpackets.commons.model.connection.AbstractBorringConnection;
import me.rochblondiaux.borringpackets.commons.model.server.ServerPacket;
import me.rochblondiaux.borringpackets.commons.netty.packets.FramedPacket;
import me.rochblondiaux.borringpackets.commons.utils.BufUtils;
import me.rochblondiaux.borringpackets.commons.utils.PacketUtils;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class BorringClientConnection extends AbstractBorringConnection<ClientPacket> {

    private final Object tickBufferLock = new Object();
    private volatile ByteBuf tickBuffer = BufUtils.direct();

    public BorringClientConnection(@NonNull SocketChannel channel) {
        super(channel);
    }

    public void write(@NonNull Object message) {
        if (message instanceof FramedPacket) {
            final FramedPacket framedPacket = (FramedPacket) message;
            synchronized (tickBufferLock) {
                if (tickBuffer.refCnt() == 0)
                    return;
                final ByteBuf body = framedPacket.getBody();
                tickBuffer.writeBytes(body, body.readerIndex(), body.readableBytes());
            }
            return;
        } else if (message instanceof ServerPacket) {
            ServerPacket packet = (ServerPacket) message;

            synchronized (tickBufferLock) {
                if (tickBuffer.refCnt() == 0)
                    return;
                PacketUtils.writeFramedPacket(tickBuffer, packet);
            }
            return;
        } else if (message instanceof ByteBuf) {
            synchronized (tickBufferLock) {
                if (tickBuffer.refCnt() == 0)
                    return;
                tickBuffer.writeBytes((ByteBuf) message);
            }
            return;
        }
        throw new UnsupportedOperationException("type " + message.getClass() + " is not supported");
    }

    public void writeWaitingPackets() {
        if (tickBuffer.writerIndex() == 0)
            // Nothing to write
            return;

        // Retrieve safe copy
        final ByteBuf copy;
        synchronized (tickBufferLock) {
            if (tickBuffer.refCnt() == 0)
                return;
            copy = tickBuffer;
            tickBuffer = tickBuffer.alloc().buffer(tickBuffer.writerIndex());
        }

        // Write copied buffer to netty
        ChannelFuture channelFuture = channel.write(new FramedPacket(copy));
        channelFuture.addListener(future -> copy.release());

        // Netty debug
        channelFuture.addListener(future -> {
            if (!future.isSuccess() && channel.isActive())
                future.cause().printStackTrace();
        });
    }

    public void flush() {
        final int bufferSize = tickBuffer.writerIndex();
        if (bufferSize <= 0 || !channel.isActive()) return;
        writeWaitingPackets();
        channel.flush();
    }

    public void releaseTickBuffer() {
        synchronized (tickBufferLock) {
            tickBuffer.release();
        }
    }
}
