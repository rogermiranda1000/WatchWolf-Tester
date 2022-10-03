package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CandleCake(short id) {
		super(id, "CANDLE_CAKE");
	}

	public CandleCake(int id) {
		this((short) id);
	}

	private CandleCake(CandleCake old) {
		this(old.id);
	}

}