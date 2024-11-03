package net.minecraft.src;

import java.util.ArrayList;

public class BlockPistonBase extends Block {
	private boolean isSticky;
	private boolean ignoreUpdates;

	public BlockPistonBase(int i1, int i2, boolean z3) {
		super(i1, i2, Material.piston);
		this.isSticky = z3;
		this.setStepSound(soundStoneFootstep);
		this.setHardness(0.5F);
	}

	public int getBlockTextureFromSideAndMetadata(int i1, int i2) {
		int i3 = getOrientation(i2);
		return i3 > 5 ? this.blockIndexInTexture : (i1 == i3 ? (!isExtended(i2) && this.minX <= 0.0D && this.minY <= 0.0D && this.minZ <= 0.0D && this.maxX >= 1.0D && this.maxY >= 1.0D && this.maxZ >= 1.0D ? this.blockIndexInTexture : 110) : (i1 == PistonBlockTextures.field_31052_a[i3] ? 109 : 108));
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public boolean blockActivated(World world1, int i2, int i3, int i4, EntityPlayer entityPlayer5) {
		return false;
	}

	public void onBlockPlacedBy(World world1, int i2, int i3, int i4, EntityLiving entityLiving5) {
		int i6 = determineOrientation(world1, i2, i3, i4, (EntityPlayer)entityLiving5);
		world1.setBlockMetadataWithNotify(i2, i3, i4, i6);
		if(!world1.singleplayerWorld) {
			this.updatePistonState(world1, i2, i3, i4);
		}

	}

	public void onNeighborBlockChange(World world1, int i2, int i3, int i4, int i5) {
		if(!world1.singleplayerWorld && !this.ignoreUpdates) {
			this.updatePistonState(world1, i2, i3, i4);
		}

	}

	public void onBlockAdded(World world1, int i2, int i3, int i4) {
		if(!world1.singleplayerWorld && world1.getBlockTileEntity(i2, i3, i4) == null) {
			this.updatePistonState(world1, i2, i3, i4);
		}

	}

	private void updatePistonState(World world1, int i2, int i3, int i4) {
		int i5 = world1.getBlockMetadata(i2, i3, i4);
		int i6 = getOrientation(i5);
		boolean z7 = this.isPowered(world1, i2, i3, i4, i6);
		if(i5 != 7) {
			if(z7 && !isExtended(i5)) {
				if(canExtend(world1, i2, i3, i4, i6)) {
					world1.setBlockMetadata(i2, i3, i4, i6 | 8);
					world1.playNoteAt(i2, i3, i4, 0, i6);
				}
			} else if(!z7 && isExtended(i5)) {
				world1.setBlockMetadata(i2, i3, i4, i6);
				world1.playNoteAt(i2, i3, i4, 1, i6);
			}

		}
	}

	private boolean isPowered(World world1, int i2, int i3, int i4, int i5) {
		return i5 != 0 && world1.isBlockIndirectlyProvidingPowerTo(i2, i3 - 1, i4, 0) ? true : (i5 != 1 && world1.isBlockIndirectlyProvidingPowerTo(i2, i3 + 1, i4, 1) ? true : (i5 != 2 && world1.isBlockIndirectlyProvidingPowerTo(i2, i3, i4 - 1, 2) ? true : (i5 != 3 && world1.isBlockIndirectlyProvidingPowerTo(i2, i3, i4 + 1, 3) ? true : (i5 != 5 && world1.isBlockIndirectlyProvidingPowerTo(i2 + 1, i3, i4, 5) ? true : (i5 != 4 && world1.isBlockIndirectlyProvidingPowerTo(i2 - 1, i3, i4, 4) ? true : (world1.isBlockIndirectlyProvidingPowerTo(i2, i3, i4, 0) ? true : (world1.isBlockIndirectlyProvidingPowerTo(i2, i3 + 2, i4, 1) ? true : (world1.isBlockIndirectlyProvidingPowerTo(i2, i3 + 1, i4 - 1, 2) ? true : (world1.isBlockIndirectlyProvidingPowerTo(i2, i3 + 1, i4 + 1, 3) ? true : (world1.isBlockIndirectlyProvidingPowerTo(i2 - 1, i3 + 1, i4, 4) ? true : world1.isBlockIndirectlyProvidingPowerTo(i2 + 1, i3 + 1, i4, 5)))))))))));
	}

	public void playBlock(World world1, int i2, int i3, int i4, int i5, int i6) {
		this.ignoreUpdates = true;
		if(i5 == 0) {
			if(this.tryExtend(world1, i2, i3, i4, i6)) {
				world1.setBlockMetadataWithNotify(i2, i3, i4, i6 | 8);
				world1.playSoundEffect((double)i2 + 0.5D, (double)i3 + 0.5D, (double)i4 + 0.5D, "tile.piston.out", 0.5F, world1.rand.nextFloat() * 0.25F + 0.6F);
			}
		} else if(i5 == 1) {
			TileEntity tileEntity8 = world1.getBlockTileEntity(i2 + PistonBlockTextures.field_31051_b[i6], i3 + PistonBlockTextures.field_31054_c[i6], i4 + PistonBlockTextures.field_31053_d[i6]);
			if(tileEntity8 != null && tileEntity8 instanceof TileEntityPiston) {
				((TileEntityPiston)tileEntity8).clearPistonTileEntity();
			}

			world1.setBlockAndMetadata(i2, i3, i4, Block.pistonMoving.blockID, i6);
			world1.setBlockTileEntity(i2, i3, i4, BlockPistonMoving.getTileEntity(this.blockID, i6, i6, false, true));
			if(this.isSticky) {
				int i9 = i2 + PistonBlockTextures.field_31051_b[i6] * 2;
				int i10 = i3 + PistonBlockTextures.field_31054_c[i6] * 2;
				int i11 = i4 + PistonBlockTextures.field_31053_d[i6] * 2;
				int i12 = world1.getBlockId(i9, i10, i11);
				int i13 = world1.getBlockMetadata(i9, i10, i11);
				boolean z14 = false;
				if(i12 == Block.pistonMoving.blockID) {
					TileEntity tileEntity15 = world1.getBlockTileEntity(i9, i10, i11);
					if(tileEntity15 != null && tileEntity15 instanceof TileEntityPiston) {
						TileEntityPiston tileEntityPiston16 = (TileEntityPiston)tileEntity15;
						if(tileEntityPiston16.func_31008_d() == i6 && tileEntityPiston16.func_31010_c()) {
							tileEntityPiston16.clearPistonTileEntity();
							i12 = tileEntityPiston16.getStoredBlockID();
							i13 = tileEntityPiston16.func_31005_e();
							z14 = true;
						}
					}
				}

				if(z14 || i12 <= 0 || !canPushBlock(i12, world1, i9, i10, i11, false) || Block.blocksList[i12].getMobilityFlag() != 0 && i12 != Block.pistonBase.blockID && i12 != Block.pistonStickyBase.blockID) {
					if(!z14) {
						this.ignoreUpdates = false;
						world1.setBlockWithNotify(i2 + PistonBlockTextures.field_31051_b[i6], i3 + PistonBlockTextures.field_31054_c[i6], i4 + PistonBlockTextures.field_31053_d[i6], 0);
						this.ignoreUpdates = true;
					}
				} else {
					this.ignoreUpdates = false;
					world1.setBlockWithNotify(i9, i10, i11, 0);
					this.ignoreUpdates = true;
					i2 += PistonBlockTextures.field_31051_b[i6];
					i3 += PistonBlockTextures.field_31054_c[i6];
					i4 += PistonBlockTextures.field_31053_d[i6];
					world1.setBlockAndMetadata(i2, i3, i4, Block.pistonMoving.blockID, i13);
					world1.setBlockTileEntity(i2, i3, i4, BlockPistonMoving.getTileEntity(i12, i13, i6, false, false));
				}
			} else {
				this.ignoreUpdates = false;
				world1.setBlockWithNotify(i2 + PistonBlockTextures.field_31051_b[i6], i3 + PistonBlockTextures.field_31054_c[i6], i4 + PistonBlockTextures.field_31053_d[i6], 0);
				this.ignoreUpdates = true;
			}

			world1.playSoundEffect((double)i2 + 0.5D, (double)i3 + 0.5D, (double)i4 + 0.5D, "tile.piston.in", 0.5F, world1.rand.nextFloat() * 0.15F + 0.6F);
		}

		this.ignoreUpdates = false;
	}

	public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess1, int i2, int i3, int i4) {
		int i5 = iBlockAccess1.getBlockMetadata(i2, i3, i4);
		if(isExtended(i5)) {
			switch(getOrientation(i5)) {
			case 0:
				this.setBlockBounds(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
				break;
			case 1:
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
				break;
			case 2:
				this.setBlockBounds(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
				break;
			case 3:
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
				break;
			case 4:
				this.setBlockBounds(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				break;
			case 5:
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
			}
		} else {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		}

	}

	public void getCollidingBoundingBoxes(World world1, int i2, int i3, int i4, AxisAlignedBB axisAlignedBB5, ArrayList arrayList6) {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		super.getCollidingBoundingBoxes(world1, i2, i3, i4, axisAlignedBB5, arrayList6);
	}

	public boolean isACube() {
		return false;
	}

	public static int getOrientation(int i0) {
		return i0 & 7;
	}

	public static boolean isExtended(int i0) {
		return (i0 & 8) != 0;
	}

	private static int determineOrientation(World world0, int i1, int i2, int i3, EntityPlayer entityPlayer4) {
		if(MathHelper.abs((float)entityPlayer4.posX - (float)i1) < 2.0F && MathHelper.abs((float)entityPlayer4.posZ - (float)i3) < 2.0F) {
			double d5 = entityPlayer4.posY + 1.82D - (double)entityPlayer4.yOffset;
			if(d5 - (double)i2 > 2.0D) {
				return 1;
			}

			if((double)i2 - d5 > 0.0D) {
				return 0;
			}
		}

		int i7 = MathHelper.floor_double((double)(entityPlayer4.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		return i7 == 0 ? 2 : (i7 == 1 ? 5 : (i7 == 2 ? 3 : (i7 == 3 ? 4 : 0)));
	}

	private static boolean canPushBlock(int i0, World world1, int i2, int i3, int i4, boolean z5) {
		if(i0 == Block.obsidian.blockID) {
			return false;
		} else {
			if(i0 != Block.pistonBase.blockID && i0 != Block.pistonStickyBase.blockID) {
				if(Block.blocksList[i0].getHardness() == -1.0F) {
					return false;
				}

				if(Block.blocksList[i0].getMobilityFlag() == 2) {
					return false;
				}

				if(!z5 && Block.blocksList[i0].getMobilityFlag() == 1) {
					return false;
				}
			} else if(isExtended(world1.getBlockMetadata(i2, i3, i4))) {
				return false;
			}

			TileEntity tileEntity6 = world1.getBlockTileEntity(i2, i3, i4);
			return tileEntity6 == null;
		}
	}

	private static boolean canExtend(World world0, int i1, int i2, int i3, int i4) {
		int i5 = i1 + PistonBlockTextures.field_31051_b[i4];
		int i6 = i2 + PistonBlockTextures.field_31054_c[i4];
		int i7 = i3 + PistonBlockTextures.field_31053_d[i4];
		int i8 = 0;

		while(true) {
			if(i8 < 13) {
				if(i6 <= 0 || i6 >= 127) {
					return false;
				}

				int i9 = world0.getBlockId(i5, i6, i7);
				if(i9 != 0) {
					if(!canPushBlock(i9, world0, i5, i6, i7, true)) {
						return false;
					}

					if(Block.blocksList[i9].getMobilityFlag() != 1) {
						if(i8 == 12) {
							return false;
						}

						i5 += PistonBlockTextures.field_31051_b[i4];
						i6 += PistonBlockTextures.field_31054_c[i4];
						i7 += PistonBlockTextures.field_31053_d[i4];
						++i8;
						continue;
					}
				}
			}

			return true;
		}
	}

	private boolean tryExtend(World world1, int i2, int i3, int i4, int i5) {
		int i6 = i2 + PistonBlockTextures.field_31051_b[i5];
		int i7 = i3 + PistonBlockTextures.field_31054_c[i5];
		int i8 = i4 + PistonBlockTextures.field_31053_d[i5];
		int i9 = 0;

		while(true) {
			int i10;
			if(i9 < 13) {
				if(i7 <= 0 || i7 >= 127) {
					return false;
				}

				i10 = world1.getBlockId(i6, i7, i8);
				if(i10 != 0) {
					if(!canPushBlock(i10, world1, i6, i7, i8, true)) {
						return false;
					}

					if(Block.blocksList[i10].getMobilityFlag() != 1) {
						if(i9 == 12) {
							return false;
						}

						i6 += PistonBlockTextures.field_31051_b[i5];
						i7 += PistonBlockTextures.field_31054_c[i5];
						i8 += PistonBlockTextures.field_31053_d[i5];
						++i9;
						continue;
					}

					Block.blocksList[i10].dropBlockAsItem(world1, i6, i7, i8, world1.getBlockMetadata(i6, i7, i8));
					world1.setBlockWithNotify(i6, i7, i8, 0);
				}
			}

			while(i6 != i2 || i7 != i3 || i8 != i4) {
				i9 = i6 - PistonBlockTextures.field_31051_b[i5];
				i10 = i7 - PistonBlockTextures.field_31054_c[i5];
				int i11 = i8 - PistonBlockTextures.field_31053_d[i5];
				int i12 = world1.getBlockId(i9, i10, i11);
				int i13 = world1.getBlockMetadata(i9, i10, i11);
				if(i12 == this.blockID && i9 == i2 && i10 == i3 && i11 == i4) {
					world1.setBlockAndMetadata(i6, i7, i8, Block.pistonMoving.blockID, i5 | (this.isSticky ? 8 : 0));
					world1.setBlockTileEntity(i6, i7, i8, BlockPistonMoving.getTileEntity(Block.pistonExtension.blockID, i5 | (this.isSticky ? 8 : 0), i5, true, false));
				} else {
					world1.setBlockAndMetadata(i6, i7, i8, Block.pistonMoving.blockID, i13);
					world1.setBlockTileEntity(i6, i7, i8, BlockPistonMoving.getTileEntity(i12, i13, i5, true, false));
				}

				i6 = i9;
				i7 = i10;
				i8 = i11;
			}

			return true;
		}
	}
}
