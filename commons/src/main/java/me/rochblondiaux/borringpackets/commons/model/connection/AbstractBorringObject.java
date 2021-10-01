package me.rochblondiaux.borringpackets.commons.model.connection;

import lombok.Data;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
public abstract class AbstractBorringObject<T extends BorringPacket> implements BorringObject<T> {

    protected final BorringConnection<T> connection;

    @Override
    public void initializeConnection() {
        this.connection.setBorringObject(this);
    }

    public void disconnect() {
        this.connection.disconnect();
    }
}
