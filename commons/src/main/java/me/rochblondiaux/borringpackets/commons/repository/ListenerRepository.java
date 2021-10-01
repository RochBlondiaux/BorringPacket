package me.rochblondiaux.borringpackets.commons.repository;

import lombok.Data;
import lombok.NonNull;
import me.rochblondiaux.borringpackets.commons.model.connection.BorringConnection;
import me.rochblondiaux.borringpackets.commons.model.listener.PacketListener;
import me.rochblondiaux.borringpackets.commons.model.packets.BorringPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
@Data
public class ListenerRepository<P extends BorringPacket, C extends BorringConnection<P>> {

    private final Map<Class<? extends P>, List<PacketListener<P, C>>> listeners = new HashMap<>();

    public void register(@NonNull Class<? extends P> clazz, @NonNull PacketListener<P, C> listener) {
        listeners.putIfAbsent(clazz, new ArrayList<>());
        listeners.get(clazz).add(listener);
    }

    public void unregister(@NonNull Class<? extends P> clazz) {
        listeners.remove(clazz);
    }

    public void unregister(@NonNull Class<? extends P> clazz, @NonNull PacketListener<P, C> listener) {
        listeners.getOrDefault(clazz, new ArrayList<>()).remove(listener);
    }

    public List<PacketListener<P, C>> getByClass(@NonNull Class<P> clazz) {
        return listeners.getOrDefault(clazz, new ArrayList<>());
    }
}
