package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class DarkOakSign extends Block {

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
	public DarkOakSign(short id) {
		super(id, "DARK_OAK_SIGN");
	}

	public DarkOakSign(int id) {
		this((short) id);
	}

	private DarkOakSign(DarkOakSign old) {
		this(old.id);
	}

}