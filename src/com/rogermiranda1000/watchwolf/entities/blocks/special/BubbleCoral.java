package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BubbleCoral extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BubbleCoral(short id) {
		super(id, "BUBBLE_CORAL");
	}

	public BubbleCoral(int id) {
		this((short) id);
	}

	private BubbleCoral(BubbleCoral old) {
		this(old.id);
	}

}