package me.rochblondiaux.borringpackets.commons.model.client;

import me.rochblondiaux.borringpackets.commons.model.client.ClientPacket;
import me.rochblondiaux.borringpackets.commons.model.packets.PacketSupplier;
import me.rochblondiaux.borringpackets.commons.model.packets.identifier.PacketIdentifier;

/**
 * @author Roch Blondiaux
 * www.roch-blondiaux.com
 */
public interface ClientPacketIdentifier extends PacketIdentifier<ClientPacket, PacketSupplier.ClientPacketSupplier> {

}
