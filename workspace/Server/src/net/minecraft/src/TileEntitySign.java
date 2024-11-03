package net.minecraft.src;

public class TileEntitySign extends TileEntity {
	public String[] signText = new String[]{"", "", "", ""};
	public int lineBeingEdited = -1;
	private boolean isEditAble = true;

	public void writeToNBT(NBTTagCompound nBTTagCompound1) {
		super.writeToNBT(nBTTagCompound1);
		nBTTagCompound1.setString("Text1", this.signText[0]);
		nBTTagCompound1.setString("Text2", this.signText[1]);
		nBTTagCompound1.setString("Text3", this.signText[2]);
		nBTTagCompound1.setString("Text4", this.signText[3]);
	}

	public void readFromNBT(NBTTagCompound nBTTagCompound1) {
		this.isEditAble = false;
		super.readFromNBT(nBTTagCompound1);

		for(int i2 = 0; i2 < 4; ++i2) {
			this.signText[i2] = nBTTagCompound1.getString("Text" + (i2 + 1));
			if(this.signText[i2].length() > 15) {
				this.signText[i2] = this.signText[i2].substring(0, 15);
			}
		}

	}

	public Packet getDescriptionPacket() {
		String[] string1 = new String[4];

		for(int i2 = 0; i2 < 4; ++i2) {
			string1[i2] = this.signText[i2];
		}

		return new Packet130UpdateSign(this.xCoord, this.yCoord, this.zCoord, string1);
	}

	public boolean getIsEditAble() {
		return this.isEditAble;
	}

	public void func_32001_a(boolean z1) {
		this.isEditAble = z1;
	}
}
