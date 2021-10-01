package me.rochblondiaux.borringpackets.commons.model.packets;

import me.rochblondiaux.borringpackets.commons.utils.binary.BinaryReader;
import me.rochblondiaux.borringpackets.commons.utils.binary.Readable;
import me.rochblondiaux.borringpackets.commons.utils.binary.Writeable;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface BorringPacket extends Readable, Writeable {

    @Override
    default void read(BinaryReader reader) {
        throw new UnsupportedOperationException("WIP: This packet is not set up to be read from the code at the moment.");
    }

    /**
     * Gets the id of this packet.
     * <p>
     * Written in the final buffer header, so it needs to match the packet id.
     *
     * @return the id of this packet
     */
    int getId();
}
