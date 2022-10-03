package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class GreenCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public GreenCandleCake(short id) {
		super(id, "GREEN_CANDLE_CAKE");
	}

	public GreenCandleCake(int id) {
		this((short) id);
	}

	private GreenCandleCake(GreenCandleCake old) {
		this(old.id);
	}

}