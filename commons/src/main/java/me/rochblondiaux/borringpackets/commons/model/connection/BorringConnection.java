package me.rochblondiaux.borringpackets.commons.model.connection;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;
import me.rochblondiaux.borringpackets.commons.model.packets.PacketTransporter;
import me.rochblondiaux.borringpackets.commons.netty.codecs.PacketCompressor;
import me.rochblondiaux.borringpackets.commons.netty.packets.FramedPacket;

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
    boolean isCompressionEnable();

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
     * Write {@link T}, {@link FramedPacket}
     * or {@link ByteBuf} to tick buffer.
     *
     * @param message object to write.
     */
    void write(@NonNull Object message);

    /**
     * Write and flush {@link T}, {@link FramedPacket}
     * or {@link ByteBuf} to tick buffer then to the netty channel.
     * <p>
     * {@link #writeWaitingPackets()}
     *
     * @param message object to write and flush.
     */
    void writeAndFlush(@NonNull Object message);

    /**
     * Write waiting packets to tick buffer then to the netty channel.
     */
    void writeWaitingPackets();

    /**
     * Flush waiting packets to network
     * {@link #writeWaitingPackets()}
     */
    void flush();

    /**
     * Disconnect object from network.
     */
    void disconnect();
}
