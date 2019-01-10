package jlan.classloader.loader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class CustomClassLoader extends ClassLoader {
	private static String PATH = "C:\\Users\\h15039.H3C\\Desktop";
	private static String FILE_TYPE = ".class";
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		FileInputStream fis;
        byte[] data = null;
        
        try {
        	File file = new File(PATH, name+FILE_TYPE);
    		System.out.println(file.getAbsolutePath());
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        }catch(Exception e) {
        	e.printStackTrace();
        }

		return defineClass(name, data, 0, data.length);
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		CustomClassLoader classLoader = new CustomClassLoader();
		Class clazz = classLoader.loadClass("Main");
		System.out.println(clazz==null?null:clazz.getName());
	}
}
