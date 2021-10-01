package me.rochblondiaux.borringpackets.server.model;

import me.rochblondiaux.borringpackets.commons.model.client.ClientPacket;
import me.rochblondiaux.borringpackets.commons.model.connection.AbstractBorringObject;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class BorringClient extends AbstractBorringObject<ClientPacket> {

    public BorringClient(BorringClientConnection connection) {
        super(connection);
    }

}
