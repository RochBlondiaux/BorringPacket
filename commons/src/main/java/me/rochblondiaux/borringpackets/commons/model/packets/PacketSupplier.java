package me.rochblondiaux.borringpackets.commons.model.packets;

import java.util.function.Supplier;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 * <p>
 * Convenient interface to supply {@link BorringPacket} implementations.
 */
public interface PacketSupplier<T extends BorringPacket> extends Supplier<T> {

}
