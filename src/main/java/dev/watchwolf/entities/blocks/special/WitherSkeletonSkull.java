package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class WitherSkeletonSkull extends Block {

	/*   --- SOCKET DATA OVERRIDE ---   */
	@Override
	public void sendSocketData(ArrayList<Byte> out) {
		SocketHelper.addShort(out, this.id);
		out.add((byte)0);
		out.add((byte)0);
		out.add((byte)0);
		SocketHelper.fill(out, 51);
	}

	/*   --- CONSTRUCTORS ---   */
	public WitherSkeletonSkull(short id) {
		super(id, "WITHER_SKELETON_SKULL");
	}

	public WitherSkeletonSkull(int id) {
		this((short) id);
	}

	private WitherSkeletonSkull(WitherSkeletonSkull old) {
		this(old.id);
	}

}