package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class DarkOakLeaves extends Block {

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
	public DarkOakLeaves(short id) {
		super(id, "DARK_OAK_LEAVES");
	}

	public DarkOakLeaves(int id) {
		this((short) id);
	}

	private DarkOakLeaves(DarkOakLeaves old) {
		this(old.id);
	}

}