package net.minecraft.src;

public class ItemMap extends ItemMapBase {
	protected ItemMap(int i1) {
		super(i1);
		this.setMaxStackSize(1);
	}

	public MapData func_28023_a(ItemStack itemStack1, World world2) {
		(new StringBuilder()).append("map_").append(itemStack1.getItemDamage()).toString();
		MapData mapData4 = (MapData)world2.func_28103_a(MapData.class, "map_" + itemStack1.getItemDamage());
		if(mapData4 == null) {
			itemStack1.setItemDamage(world2.func_28104_b("map"));
			String string3 = "map_" + itemStack1.getItemDamage();
			mapData4 = new MapData(string3);
			mapData4.field_28164_b = world2.getWorldInfo().getSpawnX();
			mapData4.field_28163_c = world2.getWorldInfo().getSpawnZ();
			mapData4.field_28161_e = 3;
			mapData4.field_28162_d = (byte)world2.worldProvider.worldType;
			mapData4.func_28146_a();
			world2.func_28102_a(string3, mapData4);
		}

		return mapData4;
	}

	public void func_28024_a(World world1, Entity entity2, MapData mapData3) {
		if(world1.worldProvider.worldType == mapData3.field_28162_d) {
			short s4 = 128;
			short s5 = 128;
			int i6 = 1 << mapData3.field_28161_e;
			int i7 = mapData3.field_28164_b;
			int i8 = mapData3.field_28163_c;
			int i9 = MathHelper.floor_double(entity2.posX - (double)i7) / i6 + s4 / 2;
			int i10 = MathHelper.floor_double(entity2.posZ - (double)i8) / i6 + s5 / 2;
			int i11 = 128 / i6;
			if(world1.worldProvider.field_4306_c) {
				i11 /= 2;
			}

			++mapData3.field_28159_g;

			for(int i12 = i9 - i11 + 1; i12 < i9 + i11; ++i12) {
				if((i12 & 15) == (mapData3.field_28159_g & 15)) {
					int i13 = 255;
					int i14 = 0;
					double d15 = 0.0D;

					for(int i17 = i10 - i11 - 1; i17 < i10 + i11; ++i17) {
						if(i12 >= 0 && i17 >= -1 && i12 < s4 && i17 < s5) {
							int i18 = i12 - i9;
							int i19 = i17 - i10;
							boolean z20 = i18 * i18 + i19 * i19 > (i11 - 2) * (i11 - 2);
							int i21 = (i7 / i6 + i12 - s4 / 2) * i6;
							int i22 = (i8 / i6 + i17 - s5 / 2) * i6;
							byte b23 = 0;
							byte b24 = 0;
							byte b25 = 0;
							int[] i26 = new int[256];
							Chunk chunk27 = world1.getChunkFromBlockCoords(i21, i22);
							int i28 = i21 & 15;
							int i29 = i22 & 15;
							int i30 = 0;
							double d31 = 0.0D;
							int i33;
							int i34;
							int i35;
							int i38;
							if(world1.worldProvider.field_4306_c) {
								i33 = i21 + i22 * 231871;
								i33 = i33 * i33 * 31287121 + i33 * 11;
								if((i33 >> 20 & 1) == 0) {
									i26[Block.dirt.blockID] += 10;
								} else {
									i26[Block.stone.blockID] += 10;
								}

								d31 = 100.0D;
							} else {
								for(i33 = 0; i33 < i6; ++i33) {
									for(i34 = 0; i34 < i6; ++i34) {
										i35 = chunk27.getHeightValue(i33 + i28, i34 + i29) + 1;
										int i36 = 0;
										if(i35 > 1) {
											boolean z37 = false;

											label164:
											while(true) {
												z37 = true;
												i36 = chunk27.getBlockID(i33 + i28, i35 - 1, i34 + i29);
												if(i36 == 0) {
													z37 = false;
												} else if(i35 > 0 && i36 > 0 && Block.blocksList[i36].blockMaterial.field_28131_A == MapColor.field_28199_b) {
													z37 = false;
												}

												if(!z37) {
													--i35;
													i36 = chunk27.getBlockID(i33 + i28, i35 - 1, i34 + i29);
												}

												if(z37) {
													if(i36 == 0 || !Block.blocksList[i36].blockMaterial.getIsLiquid()) {
														break;
													}

													i38 = i35 - 1;
													boolean z39 = false;

													while(true) {
														int i43 = chunk27.getBlockID(i33 + i28, i38--, i34 + i29);
														++i30;
														if(i38 <= 0 || i43 == 0 || !Block.blocksList[i43].blockMaterial.getIsLiquid()) {
															break label164;
														}
													}
												}
											}
										}

										d31 += (double)i35 / (double)(i6 * i6);
										++i26[i36];
									}
								}
							}

							i30 /= i6 * i6;
							int i10000 = b23 / (i6 * i6);
							i10000 = b24 / (i6 * i6);
							i10000 = b25 / (i6 * i6);
							i33 = 0;
							i34 = 0;

							for(i35 = 0; i35 < 256; ++i35) {
								if(i26[i35] > i33) {
									i34 = i35;
									i33 = i26[i35];
								}
							}

							double d41 = (d31 - d15) * 4.0D / (double)(i6 + 4) + ((double)(i12 + i17 & 1) - 0.5D) * 0.4D;
							byte b42 = 1;
							if(d41 > 0.6D) {
								b42 = 2;
							}

							if(d41 < -0.6D) {
								b42 = 0;
							}

							i38 = 0;
							if(i34 > 0) {
								MapColor mapColor44 = Block.blocksList[i34].blockMaterial.field_28131_A;
								if(mapColor44 == MapColor.field_28187_n) {
									d41 = (double)i30 * 0.1D + (double)(i12 + i17 & 1) * 0.2D;
									b42 = 1;
									if(d41 < 0.5D) {
										b42 = 2;
									}

									if(d41 > 0.9D) {
										b42 = 0;
									}
								}

								i38 = mapColor44.field_28184_q;
							}

							d15 = d31;
							if(i17 >= 0 && i18 * i18 + i19 * i19 < i11 * i11 && (!z20 || (i12 + i17 & 1) != 0)) {
								byte b45 = mapData3.field_28160_f[i12 + i17 * s4];
								byte b40 = (byte)(i38 * 4 + b42);
								if(b45 != b40) {
									if(i13 > i17) {
										i13 = i17;
									}

									if(i14 < i17) {
										i14 = i17;
									}

									mapData3.field_28160_f[i12 + i17 * s4] = b40;
								}
							}
						}
					}

					if(i13 <= i14) {
						mapData3.func_28153_a(i12, i13, i14);
					}
				}
			}

		}
	}

	public void func_28018_a(ItemStack itemStack1, World world2, Entity entity3, int i4, boolean z5) {
		if(!world2.singleplayerWorld) {
			MapData mapData6 = this.func_28023_a(itemStack1, world2);
			if(entity3 instanceof EntityPlayer) {
				EntityPlayer entityPlayer7 = (EntityPlayer)entity3;
				mapData6.func_28155_a(entityPlayer7, itemStack1);
			}

			if(z5) {
				this.func_28024_a(world2, entity3, mapData6);
			}

		}
	}

	public void func_28020_c(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		itemStack1.setItemDamage(world2.func_28104_b("map"));
		String string4 = "map_" + itemStack1.getItemDamage();
		MapData mapData5 = new MapData(string4);
		world2.func_28102_a(string4, mapData5);
		mapData5.field_28164_b = MathHelper.floor_double(entityPlayer3.posX);
		mapData5.field_28163_c = MathHelper.floor_double(entityPlayer3.posZ);
		mapData5.field_28161_e = 3;
		mapData5.field_28162_d = (byte)world2.worldProvider.worldType;
		mapData5.func_28146_a();
	}

	public Packet func_28022_b(ItemStack itemStack1, World world2, EntityPlayer entityPlayer3) {
		byte[] b4 = this.func_28023_a(itemStack1, world2).func_28154_a(itemStack1, world2, entityPlayer3);
		return b4 == null ? null : new Packet131MapData((short)Item.field_28021_bb.shiftedIndex, (short)itemStack1.getItemDamage(), b4);
	}
}
