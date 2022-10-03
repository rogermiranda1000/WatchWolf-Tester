package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class LightBlueCandleCake extends Block {

	/*   --- CONSTRUCTORS ---   */
	public LightBlueCandleCake(short id) {
		super(id, "LIGHT_BLUE_CANDLE_CAKE");
	}

	public LightBlueCandleCake(int id) {
		this((short) id);
	}

	private LightBlueCandleCake(LightBlueCandleCake old) {
		this(old.id);
	}

}