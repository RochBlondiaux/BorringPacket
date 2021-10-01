package me.rochblondiaux.borringpackets.commons.model.server;

import me.rochblondiaux.borringpackets.commons.model.client.ClientPacketIdentifier;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface ServerPacket extends BorringPacket {

    ClientPacketIdentifier getIdentifier();

    @Override
    default int getId() {
        return getIdentifier().getId();
    }
}
