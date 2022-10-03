package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class PinkCandleCake extends Block {

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