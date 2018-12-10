package tools.elasticjob;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;

public class JobListerer implements ElasticJobListener {

	public void beforeJobExecuted(ShardingContexts shardingContexts) {
		// TODO Auto-generated method stub
		System.out.println("before job executed");
	}

	public void afterJobExecuted(ShardingContexts shardingContexts) {
		// TODO Auto-generated method stub
		System.out.println("after job executed");
	}

}
