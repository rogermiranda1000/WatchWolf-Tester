package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class CyanCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public CyanCandleCake(short id) {
		super(id, "CYAN_CANDLE_CAKE");
	}

	public CyanCandleCake(int id) {
		this((short) id);
	}

	private CyanCandleCake(CyanCandleCake old) {
		this(old.id);
	}

}