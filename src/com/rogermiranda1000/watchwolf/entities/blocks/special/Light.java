package com.rogermiranda1000.watchwolf.entities.blocks.special;

import com.rogermiranda1000.watchwolf.entities.blocks.*;
import java.util.*;

public class Light extends Block {

	/*   --- CONSTRUCTORS ---   */
	public Light(int id) {
		super(id, "Light");
	}

	private Light(Light old) {
		this(old.id);
	}

}