package me.rochblondiaux.borringpackets.commons.model.connection;

import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;

import java.util.UUID;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface BorringObject<T extends BorringPacket> {

    /**
     * Initialize {@link BorringConnection}.
     */
    void initializeConnection();

    /**
     * Disconnect {@link BorringConnection} from network.
     */
    void disconnect();


    /**
     * Get {@link BorringConnection}.
     *
     * @return borring connection object.
     */
    BorringConnection<T> getConnection();

}
