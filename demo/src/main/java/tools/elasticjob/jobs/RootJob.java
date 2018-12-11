package tools.elasticjob.jobs;

import org.springframework.context.ApplicationContext;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import framework.spring.ApplicationContextHolder;
import tools.elasticjob.annotation.JobName;

public class RootJob implements SimpleJob{

	@JobName(name="root",jobClass="tools.elasticjob.jobs.RootJob")
	public void execute(ShardingContext shardingContext) {
		// TODO Auto-generated method stub
		System.out.println("***RootJob");
		ApplicationContext applicationContext = ApplicationContextHolder.getApplicationContext();
		ZookeeperRegistryCenter zookeeperRegistryCenter = (ZookeeperRegistryCenter) applicationContext.getBean("regCenter");
		zookeeperRegistryCenter.remove("/dependency");
	}

}
