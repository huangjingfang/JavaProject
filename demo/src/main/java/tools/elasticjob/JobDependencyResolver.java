package tools.elasticjob;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.I0Itec.zkclient.ZkClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
		System.out.println("AspectJ before");
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
	
	    JobName jobName = m.getAnnotation(JobName.class);
	    String name = jobName.name();
	    System.out.println("JobName:"+name);
	    DependOn dependOn = m.getAnnotation(DependOn.class);
	    if(dependOn!=null) {
	    	String dependency = dependOn.depend();
//	    	System.out.println("JobName:"+name+"; depend on:"+dependency);
	    	String[] dependencies = dependency.split(",",-1);
	    	for(String dep:dependencies) {
	    		String value = zookeeperRegistryCenter.get("/dependency/"+dep);
	    		String targetValue = ProjectProperties.get("simple.shardingTotalCount");
	    		String targetV = getStringByMax(targetValue);
	    		while(!equalSharding(value, targetV)) {
	    			try {
						Thread.sleep(1000);
						value = zookeeperRegistryCenter.get("/dependency/"+dep);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
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
	    String value = zookeeperRegistryCenter.get("/dependency/"+name);
	    if(value == null) {
	    	value = ""+shardingContext.getShardingItem();
	    }else {
	    	if(!value.contains(shardingContext.getShardingItem()+"")) {
	    		value = value+","+shardingContext.getShardingItem();
	    	}
	    }
	    System.out.println("jobName: "+name+"finished");
	    zookeeperRegistryCenter.persist("/dependency/"+name, value);
	}
	
//	@Pointcut("execution(* tools.elasticjob.jobs.*.execute(..))")
//	private void myPointCut() {
//		
//	}
//	@Before("myPointCut()")
//	public void before() {
//		System.out.println("AspectJ before");
//	}
	
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
}
