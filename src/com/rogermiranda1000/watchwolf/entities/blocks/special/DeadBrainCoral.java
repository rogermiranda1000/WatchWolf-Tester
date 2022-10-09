package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DeadBrainCoral extends Block {

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
	public DeadBrainCoral(short id) {
		super(id, "DEAD_BRAIN_CORAL");
	}

	public DeadBrainCoral(int id) {
		this((short) id);
	}

	private DeadBrainCoral(DeadBrainCoral old) {
		this(old.id);
	}

}