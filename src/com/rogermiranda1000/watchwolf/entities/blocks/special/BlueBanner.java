package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BlueBanner extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BlueBanner(short id) {
		super(id, "BLUE_BANNER");
	}

	public BlueBanner(int id) {
		this((short) id);
	}

	private BlueBanner(BlueBanner old) {
		this(old.id);
	}

}