package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class OrangeCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public OrangeCandleCake(short id) {
		super(id, "ORANGE_CANDLE_CAKE");
	}

	public OrangeCandleCake(int id) {
		this((short) id);
	}

	private OrangeCandleCake(OrangeCandleCake old) {
		this(old.id);
	}

}