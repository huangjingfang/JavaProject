package jlan.lab.proxy.staticproxy;

public class ServiceProxy implements IService {
	IService service;
	public ServiceProxy(IService service) {
		// TODO Auto-generated constructor stub
		this.service = service;
	}
	
	
	public void hello(String msg) {
		// TODO Auto-generated method stub
		System.out.println("proxy begin");
		service.hello(msg);
		System.out.println("proxy end");
	}
	
	public static void main(String[] args) {
		IService service= new ServiceProxy(new ServiceImpl());
		service.hello("World");
	}
}
