package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class DaylightDetector extends Block {

	/*   --- CONSTRUCTORS ---   */
	public DaylightDetector(int id) {
		super(id, "DaylightDetector");
	}

	private DaylightDetector(DaylightDetector old) {
		this(old.id);
	}

}