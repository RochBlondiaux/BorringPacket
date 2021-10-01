package me.rochblondiaux.borringpackets.commons.service;

import lombok.NonNull;
import me.rochblondiaux.borringpackets.commons.model.connection.BorringConnection;
import me.rochblondiaux.borringpackets.commons.model.listener.PacketListener;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;
import me.rochblondiaux.borringpackets.commons.repository.ListenerRepository;

import java.util.List;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public class ListenerService<P extends BorringPacket, C extends BorringConnection<P>> {

    private final ListenerRepository<P, C> repository;

    public ListenerService() {
        this.repository = new ListenerRepository<>();
    }

    public void handlePacketReception(@NonNull C connection, @NonNull P packet) {
        getByClass((Class<P>) packet.getClass())
                .forEach(listener -> listener.onReceive(connection, packet));
    }

    public void register(@NonNull Class<? extends P> clazz, @NonNull PacketListener<P, C> listener) {
        this.repository.register(clazz, listener);
    }

    public void unregister(@NonNull Class<? extends P> clazz) {
        this.repository.unregister(clazz);
    }

    public void unregister(@NonNull Class<? extends P> clazz, @NonNull PacketListener<P, C> listener) {
        this.repository.unregister(clazz, listener);
    }

    public List<PacketListener<P, C>> getByClass(@NonNull Class<P> clazz) {
        return this.repository.getByClass(clazz);
    }
}
