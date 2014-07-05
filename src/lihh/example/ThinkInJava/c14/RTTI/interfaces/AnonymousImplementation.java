package lihh.example.ThinkInJava.c14.RTTI.interfaces;

import java.lang.reflect.Method;

public class AnonymousImplementation {
	public static void main(String[] args) throws Throwable {
		A a = AnonymousA.makeA();
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

class AnonymousA {
	public static A makeA() {
		return new A() {
			@Override
			public void f() {
				System.out.println("public c.f()");
			}

			public void g() {
				System.out.println("public C.g()");
			}

			void u() {
				System.out.println("package C.u()");
			}

			protected void v() {
				System.out.println("protected C.v()");
			}

			protected void w() {
				System.out.println("private C.w()");
			}
		};
	}

}
