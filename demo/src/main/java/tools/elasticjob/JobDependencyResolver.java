package tools.elasticjob;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.I0Itec.zkclient.ZkClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import framework.spring.ApplicationContextHolder;
import tools.elasticjob.annotation.DependOn;
import tools.elasticjob.annotation.JobName;
import tools.elasticjob.properties.ProjectProperties;

@Aspect
@Component
public class JobDependencyResolver {

	@Around("execution(* tools.elasticjob.jobs.*.execute(..))")
	public void resolver(ProceedingJoinPoint proceedingJoinPoint) {
//		System.out.println("AspectJ before");
		Signature signature = proceedingJoinPoint.getSignature();
		MethodSignature methodSignature = null;
		if(!(signature instanceof MethodSignature)) {
			 throw new IllegalArgumentException("该注解只能用于方法");
		}
		methodSignature = (MethodSignature)signature;
		Object target = proceedingJoinPoint.getTarget();
	    Method m = null;
		try {
			m = target.getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(m == null) {
			System.out.println("Method is null");
		}
		
		ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
	    ZookeeperRegistryCenter zookeeperRegistryCenter = (ZookeeperRegistryCenter) applicationContext.getBean("regCenter");
	    JobManager manager = (JobManager) applicationContext.getBean("jobManager");
	    JobName jobName = m.getAnnotation(JobName.class);
	    String name = jobName.name();
	    Set<String> depends = manager.getGraph().traceParent(name);
	    
	    if(!depends.isEmpty()) {
	    	for(String str:depends) {
	    		String value = zookeeperRegistryCenter.get("/dependency/"+str);
	    		String targetValue;
	    		if(str.equals("root")) {
	    			targetValue = "1";
	    		}else {
	    			targetValue = ProjectProperties.get("simple.shardingTotalCount");
	    		}
	    		System.out.println("job:"+name+" is waiting for job -->"+ str+" to finish");
	    		String targetV = getStringByMax(targetValue);
	    		while(value==null||!equalSharding(value, targetV)) {
	    			try {
						Thread.sleep(1000);
						value = zookeeperRegistryCenter.get("/dependency/"+str);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Exception occor:"+e.getMessage());
						e.printStackTrace();
					}
	    		}
	    	}
	    }
	    try {
			proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ShardingContext shardingContext = (ShardingContext) proceedingJoinPoint.getArgs()[0];
	    String oldvalue = zookeeperRegistryCenter.get("/dependency/"+name);
	    String value;
	    if(oldvalue == null) {
	    	value = ""+shardingContext.getShardingItem();
	    }else {
	    	if(!oldvalue.contains(shardingContext.getShardingItem()+"")) {
	    		value = oldvalue+","+shardingContext.getShardingItem();
	    	}else {
	    		value = oldvalue;
	    	}
	    }
	    zookeeperRegistryCenter.persist("/dependency/"+name, value);
	    System.out.println("zookeeper persist:"+"/dependency/"+name+"->original value:"+value+"\tnew value:"+value);
	}
	
/*	@Pointcut("execution(* tools.elasticjob.jobs.*.execute(..))")
	private void myPointCut() {
		
	}
	@Before("myPointCut()")
	public void before() {
		System.out.println("AspectJ before");
	}
	
	@After("myPointCut()")
	public void after() {
		System.out.println("AspectJ after");
	}*/
	private String getStringByMax(String shardingItem) {
		try {
			Integer max = Integer.parseInt(shardingItem);
			StringBuilder builder = new StringBuilder();
			
			for(int i=0;i<max;i++) {
				builder = builder.append(i).append(",");
			}
			return builder.substring(0, builder.length()-1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new String();
		}
	}
	
	private static boolean equalSharding(String str0,String str1) {
		List<String> list0 = Arrays.asList(str0.split(",",-1));
		List<String> list1 = Arrays.asList(str1.split(",",-1));
		
		for(String str:list0) {
			if(!list1.contains(str)) {
				return false;
			}
		}
		for(String str:list1) {
			if(!list0.contains(str)) {
				return false;
			}
		}
		return true;
	}
	
	public static List<String> traceDependency(Class clazz) {
		List<String> nameList = new ArrayList<String>();
		List<DependNode> depends = getDependency(clazz);
		if(depends==null||depends.isEmpty()) {
			return nameList;
		}else {
			for(DependNode node:depends) {
				String name = node.getJobName();
				Class claz = node.getJobClass();
				
			}
		}
		return null;
	}
	
	public static String getDepdendStr(String path,DependNode node) {
		String name = node.jobName;
		Class claz = node.jobClass;
		return null;
	}
	
	public static List<DependNode> getDependency(Class clazz) {
		try {
			Method method = clazz.getMethod("execute", ShardingContext.class);
			if(method == null) {
				throw new Exception("function execute(ShardingContext sc) not exist!");
			}
			DependOn dependOn = method.getAnnotation(DependOn.class);
			if(dependOn == null) {
				return new ArrayList<JobDependencyResolver.DependNode>();
			}else {
				List<DependNode> list = new ArrayList<JobDependencyResolver.DependNode>();
				String jobName = dependOn.depend();
				String[] names = jobName.split(",");
				ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
				JobManager manager = (JobManager) applicationContext.getBean("jobManager");
				for(String name:names) {
					String jobClass = manager.getClass(name);
					Class claz = Class.forName(jobClass);
					DependNode node = new DependNode();
					node.setJobName(name);
					node.setJobClass(claz);
					list.add(node);
				}
				return list;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	static class DependNode {
		private String jobName;
		private Class jobClass;
		public String getJobName() {
			return jobName;
		}
		public void setJobName(String jobName) {
			this.jobName = jobName;
		}
		public Class getJobClass() {
			return jobClass;
		}
		public void setJobClass(Class jobClass) {
			this.jobClass = jobClass;
		}
	}
}
