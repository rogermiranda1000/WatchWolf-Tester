package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class RedCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public RedCandleCake(short id) {
		super(id, "RED_CANDLE_CAKE");
	}

	public RedCandleCake(int id) {
		this((short) id);
	}

	private RedCandleCake(RedCandleCake old) {
		this(old.id);
	}

}