package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PurpleCandleCake extends Block {

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
	public PurpleCandleCake(short id) {
		super(id, "PURPLE_CANDLE_CAKE");
	}

	public PurpleCandleCake(int id) {
		this((short) id);
	}

	private PurpleCandleCake(PurpleCandleCake old) {
		this(old.id);
	}

}