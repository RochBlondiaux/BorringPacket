package me.rochblondiaux.borringpackets.client.model;

import io.netty.channel.socket.SocketChannel;
import lombok.NonNull;
import me.rochblondiaux.borringpackets.commons.model.connection.AbstractBorringConnection;
import me.rochblondiaux.borringpackets.commons.model.server.ServerPacket;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class BorringServerConnection extends AbstractBorringConnection<ServerPacket> {

    public BorringServerConnection(@NonNull SocketChannel channel) {
        super(channel);
    }

}
