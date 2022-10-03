package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import com.rogermiranda1000.watchwolf.entities.SocketHelper;
import java.util.*;

public class BubbleColumn extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BubbleColumn(short id) {
		super(id, "BUBBLE_COLUMN");
	}

	public BubbleColumn(int id) {
		this((short) id);
	}

	private BubbleColumn(BubbleColumn old) {
		this(old.id);
	}

}