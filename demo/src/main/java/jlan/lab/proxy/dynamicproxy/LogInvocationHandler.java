package jlan.lab.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import jlan.lab.proxy.staticproxy.IService;
import jlan.lab.proxy.staticproxy.ServiceImpl;

public class LogInvocationHandler implements InvocationHandler {
	//动态代理过程中实际执行方法的对象
	private Object target;
	
	public Object newProxyInstance(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("dynamic proxy start -->>");
		Object ret = method.invoke(target, args);
		System.out.println("<<--dynamic proxy finished");
		return ret;
	}
	
	public static void main(String[] args) {
		IService service = (IService) new LogInvocationHandler().newProxyInstance(new ServiceImpl());
		service.hello("World");
	}

}
