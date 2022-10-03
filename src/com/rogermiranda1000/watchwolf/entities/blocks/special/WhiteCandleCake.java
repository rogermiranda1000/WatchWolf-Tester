package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class WhiteCandleCake extends Block {

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