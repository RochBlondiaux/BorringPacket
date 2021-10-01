package me.rochblondiaux.borringpackets.client.model;

import me.rochblondiaux.borringpackets.commons.model.connection.AbstractBorringObject;
import me.rochblondiaux.borringpackets.commons.model.server.ServerPacket;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class BorringServer extends AbstractBorringObject<ServerPacket> {

    public BorringServer(BorringServerConnection connection) {
        super(connection);
    }

}
