package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class Packet104WindowItems extends Packet {
	public int windowId;
	public ItemStack[] itemStack;

	public Packet104WindowItems() {
	}

	public Packet104WindowItems(int i1, List list2) {
		this.windowId = i1;
		this.itemStack = new ItemStack[list2.size()];

		for(int i3 = 0; i3 < this.itemStack.length; ++i3) {
			ItemStack itemStack4 = (ItemStack)list2.get(i3);
			this.itemStack[i3] = itemStack4 == null ? null : itemStack4.copy();
		}

	}

	public void readPacketData(DataInputStream dataInputStream1) throws IOException {
		this.windowId = dataInputStream1.readByte();
		short s2 = dataInputStream1.readShort();
		this.itemStack = new ItemStack[s2];

		for(int i3 = 0; i3 < s2; ++i3) {
			short s4 = dataInputStream1.readShort();
			if(s4 >= 0) {
				byte b5 = dataInputStream1.readByte();
				short s6 = dataInputStream1.readShort();
				this.itemStack[i3] = new ItemStack(s4, b5, s6);
			}
		}

	}

	public void writePacketData(DataOutputStream dataOutputStream1) throws IOException {
		dataOutputStream1.writeByte(this.windowId);
		dataOutputStream1.writeShort(this.itemStack.length);

		for(int i2 = 0; i2 < this.itemStack.length; ++i2) {
			if(this.itemStack[i2] == null) {
				dataOutputStream1.writeShort(-1);
			} else {
				dataOutputStream1.writeShort((short)this.itemStack[i2].itemID);
				dataOutputStream1.writeByte((byte)this.itemStack[i2].stackSize);
				dataOutputStream1.writeShort((short)this.itemStack[i2].getItemDamage());
			}
		}

	}

	public void processPacket(NetHandler netHandler1) {
		netHandler1.func_20001_a(this);
	}

	public int getPacketSize() {
		return 3 + this.itemStack.length * 5;
	}
}
