package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PinkCandleCake extends Block {

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
	public PinkCandleCake(short id) {
		super(id, "PINK_CANDLE_CAKE");
	}

	public PinkCandleCake(int id) {
		this((short) id);
	}

	private PinkCandleCake(PinkCandleCake old) {
		this(old.id);
	}

}