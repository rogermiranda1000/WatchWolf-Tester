package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PurpleCandleCake extends Block {

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