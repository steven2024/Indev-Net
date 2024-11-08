package net.minecraft.src;

import java.util.Random;

public class BlockPistonMoving extends BlockContainer {
	public BlockPistonMoving(int i1) {
		super(i1, Material.piston);
		this.setHardness(-1.0F);
	}

	protected TileEntity getBlockEntity() {
		return null;
	}

	public void onBlockAdded(World world1, int i2, int i3, int i4) {
	}

	public void onBlockRemoval(World world1, int i2, int i3, int i4) {
		TileEntity tileEntity5 = world1.getBlockTileEntity(i2, i3, i4);
		if(tileEntity5 != null && tileEntity5 instanceof TileEntityPiston) {
			((TileEntityPiston)tileEntity5).clearPistonTileEntity();
		} else {
			super.onBlockRemoval(world1, i2, i3, i4);
		}

	}

	public boolean canPlaceBlockAt(World world1, int i2, int i3, int i4) {
		return false;
	}

	public boolean canPlaceBlockOnSide(World world1, int i2, int i3, int i4, int i5) {
		return false;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean isACube() {
		return false;
	}

	public boolean blockActivated(World world1, int i2, int i3, int i4, EntityPlayer entityPlayer5) {
		if(!world1.singleplayerWorld && world1.getBlockTileEntity(i2, i3, i4) == null) {
			world1.setBlockWithNotify(i2, i3, i4, 0);
			return true;
		} else {
			return false;
		}
	}

	public int idDropped(int i1, Random random2) {
		return 0;
	}

	public void dropBlockAsItemWithChance(World world1, int i2, int i3, int i4, int i5, float f6) {
		if(!world1.singleplayerWorld) {
			TileEntityPiston tileEntityPiston7 = this.getTileEntityAtLocation(world1, i2, i3, i4);
			if(tileEntityPiston7 != null) {
				Block.blocksList[tileEntityPiston7.getStoredBlockID()].dropBlockAsItem(world1, i2, i3, i4, tileEntityPiston7.func_31005_e());
			}
		}
	}

	public void onNeighborBlockChange(World world1, int i2, int i3, int i4, int i5) {
		if(!world1.singleplayerWorld && world1.getBlockTileEntity(i2, i3, i4) == null) {
			;
		}

	}

	public static TileEntity getTileEntity(int i0, int i1, int i2, boolean z3, boolean z4) {
		return new TileEntityPiston(i0, i1, i2, z3, z4);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world1, int i2, int i3, int i4) {
		TileEntityPiston tileEntityPiston5 = this.getTileEntityAtLocation(world1, i2, i3, i4);
		if(tileEntityPiston5 == null) {
			return null;
		} else {
			float f6 = tileEntityPiston5.func_31007_a(0.0F);
			if(tileEntityPiston5.func_31010_c()) {
				f6 = 1.0F - f6;
			}

			return this.func_31032_a(world1, i2, i3, i4, tileEntityPiston5.getStoredBlockID(), f6, tileEntityPiston5.func_31008_d());
		}
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		TileEntityPiston tileEntityPiston5 = this.getTileEntityAtLocation(iBlockAccess1, i2, i3, i4);
		if(tileEntityPiston5 != null) {
			Block block6 = Block.blocksList[tileEntityPiston5.getStoredBlockID()];
			if(block6 == null || block6 == this) {
				return;
			}

			block6.setBlockBoundsBasedOnState(iBlockAccess1, i2, i3, i4);
			float f7 = tileEntityPiston5.func_31007_a(0.0F);
			if(tileEntityPiston5.func_31010_c()) {
				f7 = 1.0F - f7;
			}

			int i8 = tileEntityPiston5.func_31008_d();
			this.minX = block6.minX - (double)((float)PistonBlockTextures.field_31051_b[i8] * f7);
			this.minY = block6.minY - (double)((float)PistonBlockTextures.field_31054_c[i8] * f7);
			this.minZ = block6.minZ - (double)((float)PistonBlockTextures.field_31053_d[i8] * f7);
			this.maxX = block6.maxX - (double)((float)PistonBlockTextures.field_31051_b[i8] * f7);
			this.maxY = block6.maxY - (double)((float)PistonBlockTextures.field_31054_c[i8] * f7);
			this.maxZ = block6.maxZ - (double)((float)PistonBlockTextures.field_31053_d[i8] * f7);
		}

	}

	public AxisAlignedBB func_31032_a(World world1, int i2, int i3, int i4, int i5, float f6, int i7) {
		if(i5 != 0 && i5 != this.blockID) {
			AxisAlignedBB axisAlignedBB8 = Block.blocksList[i5].getCollisionBoundingBoxFromPool(world1, i2, i3, i4);
			if(axisAlignedBB8 == null) {
				return null;
			} else {
				axisAlignedBB8.minX -= (double)((float)PistonBlockTextures.field_31051_b[i7] * f6);
				axisAlignedBB8.maxX -= (double)((float)PistonBlockTextures.field_31051_b[i7] * f6);
				axisAlignedBB8.minY -= (double)((float)PistonBlockTextures.field_31054_c[i7] * f6);
				axisAlignedBB8.maxY -= (double)((float)PistonBlockTextures.field_31054_c[i7] * f6);
				axisAlignedBB8.minZ -= (double)((float)PistonBlockTextures.field_31053_d[i7] * f6);
				axisAlignedBB8.maxZ -= (double)((float)PistonBlockTextures.field_31053_d[i7] * f6);
				return axisAlignedBB8;
			}
		} else {
			return null;
		}
	}

	private TileEntityPiston getTileEntityAtLocation(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		TileEntity tileEntity5 = iBlockAccess1.getBlockTileEntity(i2, i3, i4);
		return tileEntity5 != null && tileEntity5 instanceof TileEntityPiston ? (TileEntityPiston)tileEntity5 : null;
	}
}
