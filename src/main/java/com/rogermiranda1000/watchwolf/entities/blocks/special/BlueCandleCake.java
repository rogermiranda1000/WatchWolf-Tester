package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BlueCandleCake extends Block {

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
	public BlueCandleCake(short id) {
		super(id, "BLUE_CANDLE_CAKE");
	}

	public BlueCandleCake(int id) {
		this((short) id);
	}

	private BlueCandleCake(BlueCandleCake old) {
		this(old.id);
	}

}