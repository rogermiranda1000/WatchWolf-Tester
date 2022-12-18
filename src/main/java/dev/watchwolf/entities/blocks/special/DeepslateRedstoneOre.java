package dev.watchwolf.entities.blocks.special;

import dev.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class DeepslateRedstoneOre extends Block {

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
	public DeepslateRedstoneOre(short id) {
		super(id, "DEEPSLATE_REDSTONE_ORE");
	}

	public DeepslateRedstoneOre(int id) {
		this((short) id);
	}

	private DeepslateRedstoneOre(DeepslateRedstoneOre old) {
		this(old.id);
	}

}