package tools.elasticjob.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import tools.elasticjob.annotation.DependOn;
import tools.elasticjob.annotation.JobName;

public class ThirdJob implements SimpleJob {

	@JobName(name="third",jobClass = "tools.elasticjob.jobs.ThirdJob")
	@DependOn(depend = "firstJob,secondJob")
	public void execute(ShardingContext shardingContext) {
		// TODO Auto-generated method stub
		System.out.println("third-job started->> shardingContext:"+shardingContext.getShardingItem());
	}

}
