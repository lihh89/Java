package lihh.example.ThinkInJava.c14.RTTI.interfaces;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HiddenImplementation {
	public static void main(String[] args) throws Throwable {
		A a = HiddenC.makeA();
		a.f();
		System.out.println(a.getClass().getName());
		// Compile error: can not find simple 'C'
		// if(a instanceof C){
		// C c = (C) a;
		// c.g();
		// }
		// Oops! Reflection still allows us to call g():
		callHiddenMethod(a, "g");
		// And even methods that are less accessible !
		callHiddenMethod(a, "u");
		callHiddenMethod(a, "v");
		callHiddenMethod(a, "w");
	}

	static void callHiddenMethod(Object a, String methodName) throws Exception {
		Method g = a.getClass().getDeclaredMethod(methodName, null);
		g.setAccessible(true);
		g.invoke(a, null);
	}
}
