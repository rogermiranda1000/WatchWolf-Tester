package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LimeCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LimeCandleCake(short id) {
		super(id, "LIME_CANDLE_CAKE");
	}

	public LimeCandleCake(int id) {
		this((short) id);
	}

	private LimeCandleCake(LimeCandleCake old) {
		this(old.id);
	}

}