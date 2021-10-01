package me.rochblondiaux.borringpackets.commons.model.listener;

import lombok.NonNull;
import me.rochblondiaux.borringpackets.commons.model.connection.BorringConnection;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface PacketListener<P extends BorringPacket, C extends BorringConnection<P>> {

    void onReceive(@NonNull C connection, @NonNull P packet);

}
