package jlan.lab.proxy.dynamicproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("start cglibProxy ->>");
		Object obj = methodProxy.invokeSuper(o, args);
		System.out.println("<<- cglibProxy finished");
		return obj;
	}
	
	public static void main(String[] args) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(CglibService.class);
		enhancer.setCallback(new CglibProxy());
		CglibService service = (CglibService) enhancer.create();
		service.say("World");
	}

}
