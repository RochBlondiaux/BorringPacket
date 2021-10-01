package me.rochblondiaux.borringpackets.commons.model.packets;


/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 * <p>
 * Reponsible for {@link BorringPacket} transport.
 */
public interface PacketTransporter<T extends BorringPacket> {

    /**
     * Send {@link BorringPacket} over network.
     *
     * @param packet {@link BorringPacket} to send.
     */
    void sendPacket(T packet);

}