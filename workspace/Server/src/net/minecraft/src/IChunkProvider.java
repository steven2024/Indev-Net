package net.minecraft.src;

public interface IChunkProvider {
	boolean chunkExists(int i1, int i2);

	Chunk provideChunk(int i1, int i2);

	Chunk loadChunk(int i1, int i2);

	void populate(IChunkProvider iChunkProvider1, int i2, int i3);

	boolean saveChunks(boolean z1, IProgressUpdate iProgressUpdate2);

	boolean func_361_a();

	boolean func_364_b();
}
