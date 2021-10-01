package me.rochblondiaux.borringpackets.commons.model.client;

import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface ClientPacket extends BorringPacket {

    ClientPacketIdentifier getIdentifier();

    @Override
    default int getId() {
        return getIdentifier().getId();
    }
}
