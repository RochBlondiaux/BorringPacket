package me.rochblondiaux.borringpackets.commons.model.packets;

import me.rochblondiaux.borringpackets.commons.model.client.ClientPacket;
import me.rochblondiaux.borringpackets.commons.model.server.ServerPacket;

import java.util.function.Supplier;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 * <p>
 * Convenient interface to supply {@link BorringPacket} implementations.
 */
public interface PacketSupplier<T extends BorringPacket> extends Supplier<T> {

    /**
     * Convenient interface to supply a {@link ClientPacket}.
     */
    interface ClientPacketSupplier extends PacketSupplier<ClientPacket> {
    }

    /**
     * Convenient interface to supply a {@link ServerPacket}.
     */
    interface ServerPacketSupplier extends PacketSupplier<ServerPacket> {
    }

}
