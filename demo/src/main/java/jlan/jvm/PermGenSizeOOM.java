package jlan.jvm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class PermGenSizeOOM {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		jdk7();
		
	}
	private static void jdk6() {
		List<String> list = new ArrayList<>();
		int i = 0;
		while(true) {
			//仅在JDK1.6之前会出现OOM的错误，在JDK7之后可以一直执行下去，list的所有指针指向常量池中的同一个字符串
			list.add(String.valueOf("1").intern());
			i++;
			if(i==10) {
				break;
			}
		}
		System.out.println(list.get(0)==list.get(1));
	}
	
	private static void jdk7() {
		while(true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(HeapOOM.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					// TODO Auto-generated method stub
					return proxy.invokeSuper(obj, args);
				}
			});
			enhancer.create();
		}
	}
}
