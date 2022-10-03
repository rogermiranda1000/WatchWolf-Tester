package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class BubbleColumn extends Block {

	/*   --- CONSTRUCTORS ---   */
	public BubbleColumn(int id) {
		super(id, "BubbleColumn");
	}

	private BubbleColumn(BubbleColumn old) {
		this(old.id);
	}

}