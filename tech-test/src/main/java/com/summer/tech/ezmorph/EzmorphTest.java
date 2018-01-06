package com.summer.tech.ezmorph;

import java.util.Calendar;
import java.util.GregorianCalendar;

import net.sf.ezmorph.MorphUtils;
import net.sf.ezmorph.MorpherRegistry;
import net.sf.ezmorph.array.ObjectArrayMorpher;
import net.sf.ezmorph.bean.BeanMorpher;
import net.sf.ezmorph.object.BooleanObjectMorpher;
import net.sf.ezmorph.object.StringMorpher;
import net.sf.ezmorph.primitive.IntMorpher;

import org.junit.Assert;

public class EzmorphTest {

	public static void main(String[] args) {
		new EzmorphTest().morpher();
	}

	public void morpher() {
		int i = new IntMorpher().morph("123");
		Assert.assertEquals(123, i); // true!
		String str = (String) StringMorpher.getInstance().morph(
				new Integer(123));
		Assert.assertEquals("123", str); // true!
		Boolean[] bools = (Boolean[]) new ObjectArrayMorpher(
				new BooleanObjectMorpher()).morph(new String[] { "true",
				"false" });
		Assert.assertEquals(Boolean.TRUE, bools[0]); // true!
		Assert.assertEquals(Boolean.FALSE, bools[1]); // true!
		MorpherRegistry morperRegistry = new MorpherRegistry();
		MorphUtils.registerStandardMorphers(morperRegistry);
		Integer x = (Integer) morperRegistry.morph(Integer.class, "2");
		Assert.assertEquals(new Integer(2), x); // true!

		Calendar dynaBean = Calendar.getInstance(); // initialized elsewhere
		System.out.println(dynaBean.getTime());
		morperRegistry.registerMorpher(new BeanMorpher(GregorianCalendar.class,
				morperRegistry));
		Calendar myBean = (Calendar) morperRegistry.morph(
				GregorianCalendar.class, dynaBean);
	}

}
