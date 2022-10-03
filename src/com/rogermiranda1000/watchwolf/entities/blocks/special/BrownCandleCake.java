package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BrownCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BrownCandleCake(short id) {
		super(id, "BROWN_CANDLE_CAKE");
	}

	public BrownCandleCake(int id) {
		this((short) id);
	}

	private BrownCandleCake(BrownCandleCake old) {
		this(old.id);
	}

}