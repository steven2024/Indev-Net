package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet106Transaction extends Packet {
	public int windowId;
	public short shortWindowId;
	public boolean field_20035_c;

	public Packet106Transaction() {
	}

	public Packet106Transaction(int i1, short s2, boolean z3) {
		this.windowId = i1;
		this.shortWindowId = s2;
		this.field_20035_c = z3;
	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_20008_a(this);
	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.windowId = dataInputStream1.readByte();
		this.shortWindowId = dataInputStream1.readShort();
		this.field_20035_c = dataInputStream1.readByte() != 0;
	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeByte(this.windowId);
		dataOutputStream1.writeShort(this.shortWindowId);
		dataOutputStream1.writeByte(this.field_20035_c ? 1 : 0);
	}

	public int getPacketSize() {
		return 4;
	}
}
