package me.rochblondiaux.borringpackets.commons.model.connection;

import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.SocketChannel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;
import me.rochblondiaux.borringpackets.commons.netty.NettyOptions;
import me.rochblondiaux.borringpackets.commons.netty.codecs.PacketCompressor;
import me.rochblondiaux.borringpackets.commons.utils.PacketUtils;

import java.net.SocketAddress;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Getter
public abstract class AbstractBorringConnection<T extends BorringPacket> implements BorringConnection<T> {

    protected final SocketChannel channel;
    protected final SocketAddress remoteAddress;
    protected boolean compressionEnabled;

    @Setter
    protected BorringObject<T> borringObject;

    public AbstractBorringConnection(@NonNull SocketChannel channel) {
        this.channel = channel;
        this.remoteAddress = channel.remoteAddress();
        this.compressionEnabled = false;
    }

    @Override
    public void startCompression() {
        if (compressionEnabled) throw new IllegalStateException("Compression is already enabled!");
        final int threshold = PacketUtils.COMPRESSION_THRESHOLD;
        if (threshold == 0)
            throw new IllegalStateException("Compression cannot be enabled because the threshold is equal to 0");
        this.compressionEnabled = true;
        channel.pipeline().addAfter(NettyOptions.FRAMER_HANDLER_NAME, NettyOptions.COMPRESSOR_HANDLER_NAME, new PacketCompressor(threshold));
    }

    @Override
    public void stopCompression() {
        if (!compressionEnabled) throw new IllegalStateException("Compression is not enabled!");
        this.compressionEnabled = false;
        channel.pipeline().remove(NettyOptions.COMPRESSOR_HANDLER_NAME);
        channel.pipeline().remove(NettyOptions.FRAMER_HANDLER_NAME);
    }

    @Override
    public void sendPacket(@NonNull T packet) {
        if (channel.isActive())
            writeAndFlush(packet);
    }

    @Override
    public void writeAndFlush(@NonNull Object message) {
        ChannelFuture channelFuture = channel.writeAndFlush(message);

        channelFuture.addListener(future -> {
            if (!future.isSuccess() && channel.isActive())
                future.cause().printStackTrace();
        });
    }

    @Override
    public void disconnect() {
        this.channel.close();
    }
}
