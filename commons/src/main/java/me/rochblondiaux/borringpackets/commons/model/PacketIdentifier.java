package me.rochblondiaux.borringpackets.commons.model;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface PacketIdentifier<T extends BorringPacket, S extends PacketSupplier<T>> {

    /**
     * Get {@link BorringPacket} identifier.
     *
     * @return packet identifier.
     */
    int getId();

    /**
     * Get {@link PacketSupplier} for targeted {@link BorringPacket}
     *
     * @return packet supplier.
     */
    S getSupplier();

}
