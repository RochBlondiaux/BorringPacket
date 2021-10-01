package me.rochblondiaux.borringpackets.commons.model.connection;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;
import me.rochblondiaux.borringpackets.commons.model.packets.PacketTransporter;
import me.rochblondiaux.borringpackets.commons.netty.codecs.PacketCompressor;
import me.rochblondiaux.borringpackets.commons.netty.packets.FramedPacket;

import java.net.SocketAddress;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 * <p>
 * Represents a networking connection with Netty.
 */
public interface BorringConnection<T extends BorringPacket> extends PacketTransporter<T> {

    /**
     * Enables compression and add a new codecs to the pipeline.
     *
     * @throws IllegalStateException if encryption is already enabled for this connection
     */
    void startCompression();

    /**
     * Disable compression and remove {@link PacketCompressor} from connection pipeline.
     */
    void stopCompression();

    /**
     * Check if compression is enabled or not.
     *
     * @return compression state.
     */
    boolean isCompressionEnabled();

    /**
     * Writes a packet to the connection channel.
     * <p>
     * All packets are flushed during object update
     *
     * @param packet the packet to write
     */
    @Override
    void sendPacket(@NonNull T packet);

    /**
     * Write and flush {@link T}, {@link FramedPacket}
     * or {@link ByteBuf} to tick buffer then to the netty channel.
     *
     * @param message object to write and flush.
     */
    void writeAndFlush(@NonNull Object message);


    /**
     * Disconnect object from network.
     */
    void disconnect();

    /**
     * Get {@link BorringObject}
     *
     * @return linked object.
     */
    BorringObject<T> getBorringObject();

    /**
     * Set linked {@link BorringObject}
     *
     * @param borringObject linked object.
     */
    void setBorringObject(@NonNull BorringObject<T> borringObject);

    /**
     * Get remote address.
     *
     * @return {@link SocketAddress}
     */
    SocketAddress getRemoteAddress();
}
