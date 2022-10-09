package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RespawnAnchor extends Block {

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
	public RespawnAnchor(short id) {
		super(id, "RESPAWN_ANCHOR");
	}

	public RespawnAnchor(int id) {
		this((short) id);
	}

	private RespawnAnchor(RespawnAnchor old) {
		this(old.id);
	}

}