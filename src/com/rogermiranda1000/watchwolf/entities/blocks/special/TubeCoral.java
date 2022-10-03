package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class TubeCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public TubeCoral(short id) {
		super(id, "TUBE_CORAL");
	}

	public TubeCoral(int id) {
		this((short) id);
	}

	private TubeCoral(TubeCoral old) {
		this(old.id);
	}

}