package jlan.classloader;

import org.springframework.context.ApplicationContext;

import jlan.jvm.PrintGc;

public class ClassLoaderDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ClassLoaderDemo.class.getClassLoader().getClass().getName());
		System.out.println(Object.class.getClassLoader());
		System.out.println(Thread.class.getClassLoader());
		System.out.println(ApplicationContext.class.getClassLoader().getClass().getName());
		System.out.println(PrintGc.class.getClassLoader().getClass().getName());
		
		System.out.println(ClassLoaderDemo.class.getClassLoader().getParent().getClass().getName());
		//ExtClassLoader父加载器为bootstrapClassLoader,故无法获取
		//System.out.println(ClassLoaderDemo.class.getClassLoader().getParent().getParent().getClass().getName());
		
		try {
			Class cipher = ClassLoaderDemo.class.getClassLoader().getParent().loadClass("com.sun.crypto.provider.AESCipher");
			System.out.println(cipher==null?null:cipher.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("java.class.path"));
	}

}
