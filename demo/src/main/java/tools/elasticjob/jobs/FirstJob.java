package tools.elasticjob.jobs;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

import tools.elasticjob.annotation.DependOn;
import tools.elasticjob.annotation.JobName;


public class FirstJob implements SimpleJob {

	@JobName(name ="firstJob",jobClass="tools.elasticjob.jobs.FirstJob")
	@DependOn(depend="root")
	public void execute(ShardingContext shardingContext) {
		// TODO Auto-generated method stub
		System.out.println("first-job started->> shardingContext:"+shardingContext.getShardingItem());
		/*System.out.println("1 shardingContext:"+shardingContext.getShardingItem());
		System.out.println("1 ready to snap for 10 times:");
		for(int i=0;i<10;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("First job snap "+ i+ " succeed!");
		}
		System.out.println("<<-First job finished");*/
	}

}
