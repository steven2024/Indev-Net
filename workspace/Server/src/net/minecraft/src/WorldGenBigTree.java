package net.minecraft.src;

import java.util.Random;

public class WorldGenBigTree extends WorldGenerator {
	static final byte[] field_760_a = new byte[]{(byte)2, (byte)0, (byte)0, (byte)1, (byte)2, (byte)1};
	Random random0 = new Random();
	World worldObj;
	int[] basePos = new int[]{0, 0, 0};
	int field_756_e = 0;
	int height;
	double field_754_g = 0.618D;
	double field_753_h = 1.0D;
	double field_752_i = 0.381D;
	double field_751_j = 1.0D;
	double field_750_k = 1.0D;
	int field_749_l = 1;
	int field_748_m = 12;
	int field_747_n = 4;
	int[][] field_746_o;

	void func_424_a() {
		this.height = (int)((double)this.field_756_e * this.field_754_g);
		if(this.height >= this.field_756_e) {
			this.height = this.field_756_e - 1;
		}

		int i1 = (int)(1.382D + Math.pow(this.field_750_k * (double)this.field_756_e / 13.0D, 2.0D));
		if(i1 < 1) {
			i1 = 1;
		}

		int[][] i2 = new int[i1 * this.field_756_e][4];
		int i3 = this.basePos[1] + this.field_756_e - this.field_747_n;
		int i4 = 1;
		int i5 = this.basePos[1] + this.height;
		int i6 = i3 - this.basePos[1];
		i2[0][0] = this.basePos[0];
		i2[0][1] = i3;
		i2[0][2] = this.basePos[2];
		i2[0][3] = i5;
		--i3;

		while(true) {
			while(i6 >= 0) {
				int i7 = 0;
				float f8 = this.func_431_a(i6);
				if(f8 < 0.0F) {
					--i3;
					--i6;
				} else {
					for(double d9 = 0.5D; i7 < i1; ++i7) {
						double d11 = this.field_751_j * (double)f8 * ((double)this.random0.nextFloat() + 0.328D);
						double d13 = (double)this.random0.nextFloat() * 2.0D * 3.14159D;
						int i15 = MathHelper.floor_double(d11 * Math.sin(d13) + (double)this.basePos[0] + d9);
						int i16 = MathHelper.floor_double(d11 * Math.cos(d13) + (double)this.basePos[2] + d9);
						int[] i17 = new int[]{i15, i3, i16};
						int[] i18 = new int[]{i15, i3 + this.field_747_n, i16};
						if(this.func_427_a(i17, i18) == -1) {
							int[] i19 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
							double d20 = Math.sqrt(Math.pow((double)Math.abs(this.basePos[0] - i17[0]), 2.0D) + Math.pow((double)Math.abs(this.basePos[2] - i17[2]), 2.0D));
							double d22 = d20 * this.field_752_i;
							if((double)i17[1] - d22 > (double)i5) {
								i19[1] = i5;
							} else {
								i19[1] = (int)((double)i17[1] - d22);
							}

							if(this.func_427_a(i19, i17) == -1) {
								i2[i4][0] = i15;
								i2[i4][1] = i3;
								i2[i4][2] = i16;
								i2[i4][3] = i19[1];
								++i4;
							}
						}
					}

					--i3;
					--i6;
				}
			}

			this.field_746_o = new int[i4][4];
			System.arraycopy(i2, 0, this.field_746_o, 0, i4);
			return;
		}
	}

	void func_426_a(int i1, int i2, int i3, float f4, byte b5, int i6) {
		int i7 = (int)((double)f4 + 0.618D);
		byte b8 = field_760_a[b5];
		byte b9 = field_760_a[b5 + 3];
		int[] i10 = new int[]{i1, i2, i3};
		int[] i11 = new int[]{0, 0, 0};
		int i12 = -i7;
		int i13 = -i7;

		label32:
		for(i11[b5] = i10[b5]; i12 <= i7; ++i12) {
			i11[b8] = i10[b8] + i12;
			i13 = -i7;

			while(true) {
				while(true) {
					if(i13 > i7) {
						continue label32;
					}

					double d15 = Math.sqrt(Math.pow((double)Math.abs(i12) + 0.5D, 2.0D) + Math.pow((double)Math.abs(i13) + 0.5D, 2.0D));
					if(d15 > (double)f4) {
						++i13;
					} else {
						i11[b9] = i10[b9] + i13;
						int i14 = this.worldObj.getBlockId(i11[0], i11[1], i11[2]);
						if(i14 != 0 && i14 != 18) {
							++i13;
						} else {
							this.worldObj.setBlock(i11[0], i11[1], i11[2], i6);
							++i13;
						}
					}
				}
			}
		}

	}

	float func_431_a(int i1) {
		if((double)i1 < (double)((float)this.field_756_e) * 0.3D) {
			return -1.618F;
		} else {
			float f2 = (float)this.field_756_e / 2.0F;
			float f3 = (float)this.field_756_e / 2.0F - (float)i1;
			float f4;
			if(f3 == 0.0F) {
				f4 = f2;
			} else if(Math.abs(f3) >= f2) {
				f4 = 0.0F;
			} else {
				f4 = (float)Math.sqrt(Math.pow((double)Math.abs(f2), 2.0D) - Math.pow((double)Math.abs(f3), 2.0D));
			}

			f4 *= 0.5F;
			return f4;
		}
	}

	float func_429_b(int i1) {
		return i1 >= 0 && i1 < this.field_747_n ? (i1 != 0 && i1 != this.field_747_n - 1 ? 3.0F : 2.0F) : -1.0F;
	}

	void func_423_a(int i1, int i2, int i3) {
		int i4 = i2;

		for(int i5 = i2 + this.field_747_n; i4 < i5; ++i4) {
			float f6 = this.func_429_b(i4 - i2);
			this.func_426_a(i1, i4, i3, f6, (byte)1, 18);
		}

	}

	void func_425_a(int[] i1, int[] i2, int i3) {
		int[] i4 = new int[]{0, 0, 0};
		byte b5 = 0;

		byte b6;
		for(b6 = 0; b5 < 3; ++b5) {
			i4[b5] = i2[b5] - i1[b5];
			if(Math.abs(i4[b5]) > Math.abs(i4[b6])) {
				b6 = b5;
			}
		}

		if(i4[b6] != 0) {
			byte b7 = field_760_a[b6];
			byte b8 = field_760_a[b6 + 3];
			byte b9;
			if(i4[b6] > 0) {
				b9 = 1;
			} else {
				b9 = -1;
			}

			double d10 = (double)i4[b7] / (double)i4[b6];
			double d12 = (double)i4[b8] / (double)i4[b6];
			int[] i14 = new int[]{0, 0, 0};
			int i15 = 0;

			for(int i16 = i4[b6] + b9; i15 != i16; i15 += b9) {
				i14[b6] = MathHelper.floor_double((double)(i1[b6] + i15) + 0.5D);
				i14[b7] = MathHelper.floor_double((double)i1[b7] + (double)i15 * d10 + 0.5D);
				i14[b8] = MathHelper.floor_double((double)i1[b8] + (double)i15 * d12 + 0.5D);
				this.worldObj.setBlock(i14[0], i14[1], i14[2], i3);
			}

		}
	}

	void func_421_b() {
		int i1 = 0;

		for(int i2 = this.field_746_o.length; i1 < i2; ++i1) {
			int i3 = this.field_746_o[i1][0];
			int i4 = this.field_746_o[i1][1];
			int i5 = this.field_746_o[i1][2];
			this.func_423_a(i3, i4, i5);
		}

	}

	boolean func_430_c(int i1) {
		return (double)i1 >= (double)this.field_756_e * 0.2D;
	}

	void func_432_c() {
		int i1 = this.basePos[0];
		int i2 = this.basePos[1];
		int i3 = this.basePos[1] + this.height;
		int i4 = this.basePos[2];
		int[] i5 = new int[]{i1, i2, i4};
		int[] i6 = new int[]{i1, i3, i4};
		this.func_425_a(i5, i6, 17);
		if(this.field_749_l == 2) {
			++i5[0];
			++i6[0];
			this.func_425_a(i5, i6, 17);
			++i5[2];
			++i6[2];
			this.func_425_a(i5, i6, 17);
			i5[0] += -1;
			i6[0] += -1;
			this.func_425_a(i5, i6, 17);
		}

	}

	void func_428_d() {
		int i1 = 0;
		int i2 = this.field_746_o.length;

		for(int[] i3 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]}; i1 < i2; ++i1) {
			int[] i4 = this.field_746_o[i1];
			int[] i5 = new int[]{i4[0], i4[1], i4[2]};
			i3[1] = i4[3];
			int i6 = i3[1] - this.basePos[1];
			if(this.func_430_c(i6)) {
				this.func_425_a(i3, i5, 17);
			}
		}

	}

	int func_427_a(int[] i1, int[] i2) {
		int[] i3 = new int[]{0, 0, 0};
		byte b4 = 0;

		byte b5;
		for(b5 = 0; b4 < 3; ++b4) {
			i3[b4] = i2[b4] - i1[b4];
			if(Math.abs(i3[b4]) > Math.abs(i3[b5])) {
				b5 = b4;
			}
		}

		if(i3[b5] == 0) {
			return -1;
		} else {
			byte b6 = field_760_a[b5];
			byte b7 = field_760_a[b5 + 3];
			byte b8;
			if(i3[b5] > 0) {
				b8 = 1;
			} else {
				b8 = -1;
			}

			double d9 = (double)i3[b6] / (double)i3[b5];
			double d11 = (double)i3[b7] / (double)i3[b5];
			int[] i13 = new int[]{0, 0, 0};
			int i14 = 0;

			int i15;
			for(i15 = i3[b5] + b8; i14 != i15; i14 += b8) {
				i13[b5] = i1[b5] + i14;
				i13[b6] = MathHelper.floor_double((double)i1[b6] + (double)i14 * d9);
				i13[b7] = MathHelper.floor_double((double)i1[b7] + (double)i14 * d11);
				int i16 = this.worldObj.getBlockId(i13[0], i13[1], i13[2]);
				if(i16 != 0 && i16 != 18) {
					break;
				}
			}

			return i14 == i15 ? -1 : Math.abs(i14);
		}
	}

	boolean func_422_e() {
		int[] i1 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
		int[] i2 = new int[]{this.basePos[0], this.basePos[1] + this.field_756_e - 1, this.basePos[2]};
		int i3 = this.worldObj.getBlockId(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);
		if(i3 != 2 && i3 != 3) {
			return false;
		} else {
			int i4 = this.func_427_a(i1, i2);
			if(i4 == -1) {
				return true;
			} else if(i4 < 6) {
				return false;
			} else {
				this.field_756_e = i4;
				return true;
			}
		}
	}

	public void func_420_a(double d1, double d3, double d5) {
		this.field_748_m = (int)(d1 * 12.0D);
		if(d1 > 0.5D) {
			this.field_747_n = 5;
		}

		this.field_751_j = d3;
		this.field_750_k = d5;
	}

	public boolean generate(World world1, Random random2, int i3, int i4, int i5) {
		this.worldObj = world1;
		long j6 = random2.nextLong();
		this.random0.setSeed(j6);
		this.basePos[0] = i3;
		this.basePos[1] = i4;
		this.basePos[2] = i5;
		if(this.field_756_e == 0) {
			this.field_756_e = 5 + this.random0.nextInt(this.field_748_m);
		}

		if(!this.func_422_e()) {
			return false;
		} else {
			this.func_424_a();
			this.func_421_b();
			this.func_432_c();
			this.func_428_d();
			return true;
		}
	}
}
