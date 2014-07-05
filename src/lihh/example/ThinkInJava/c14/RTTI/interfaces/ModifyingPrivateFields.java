package lihh.example.ThinkInJava.c14.RTTI.interfaces;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;

public class ModifyingPrivateFields {
	public static void main(String[] args) {
		WithPrivateFinalField pf = new WithPrivateFinalField();
		System.out.println(pf);

		try {
			Field f = pf.getClass().getDeclaredField("i");
			f.setAccessible(true);
			System.out.println("f.getInt(pf): " + f.getInt(pf));
			f.setInt(pf, 47);
			System.out.println(pf);

			f = pf.getClass().getDeclaredField("s");
			f.setAccessible(true);
			System.out.println("f.get(pf): " + f.get(pf));
			f.set(pf, "No, you're not!"); // final被修改时是安全的，不会被修改，也没有抛出异常
			System.out.println(pf);

			f = pf.getClass().getDeclaredField("s2");
			f.setAccessible(true);
			System.out.println("f.get(pf): " + f.get(pf));
			f.set(pf, "No, you're not!");
			System.out.println(pf);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class WithPrivateFinalField {
	private int i = 1;
	private final String s = "I'm totally safe";
	private String s2 = "Am I safe ?";

	@Override
	public String toString() {
		return "i=" + i + ", " + s + ", " + s2;
	}
}
