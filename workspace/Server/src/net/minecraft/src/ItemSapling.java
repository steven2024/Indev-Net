package net.minecraft.src;

public class ItemSapling extends ItemBlock {
	public ItemSapling(int i1) {
		super(i1);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	public int getMetadata(int i1) {
		return i1;
	}
}
