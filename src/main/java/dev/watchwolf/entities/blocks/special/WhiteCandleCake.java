package dev.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import dev.watchwolf.entities.SocketHelper;
import dev.watchwolf.entities.blocks.Block;

import java.util.*;

public class WhiteCandleCake extends Block {

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
	public WhiteCandleCake(short id) {
		super(id, "WHITE_CANDLE_CAKE");
	}

	public WhiteCandleCake(int id) {
		this((short) id);
	}

	private WhiteCandleCake(WhiteCandleCake old) {
		this(old.id);
	}

}