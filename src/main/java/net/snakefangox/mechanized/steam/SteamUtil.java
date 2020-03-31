package net.snakefangox.mechanized.steam;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class SteamUtil {

	public static void pushSteam(World world, Steam source, BlockPos pos, Direction side) {
		int outCount = 1;
		Steam[] out = new Steam[6];
		for (int i = 0; i < Direction.values().length; i++) {
			BlockEntity be = world.getBlockEntity(pos.offset(Direction.values()[i]));
			if (be instanceof Steam) {
				out[i] = (Steam) be;
				++outCount;
			}
		}
		
		for (int i = 0; i < out.length; i++) {
			if (out[i] == null)
				continue;
			source.removeSteam(side,
					out[i].addSteam(Direction.values()[i].getOpposite(), source.getSteamAmount(side) / outCount));
		}
	}
}
