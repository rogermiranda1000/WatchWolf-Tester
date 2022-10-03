package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BlackCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BlackCandleCake(short id) {
		super(id, "BLACK_CANDLE_CAKE");
	}

	public BlackCandleCake(int id) {
		this((short) id);
	}

	private BlackCandleCake(BlackCandleCake old) {
		this(old.id);
	}

}