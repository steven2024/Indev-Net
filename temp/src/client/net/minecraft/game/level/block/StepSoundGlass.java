package net.minecraft.game.level.block;

final class StepSoundGlass extends StepSound {
	StepSoundGlass(String string1, float f2, float f3) {
		super(string1, 1.0F, 1.0F);
	}

	public final String stepSoundDir() {
		return "random.glass";
	}
}