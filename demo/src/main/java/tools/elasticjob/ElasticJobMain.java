package tools.elasticjob;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.event.rdb.JobEventRdbConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import tools.elasticjob.jobs.FirstJob;
import tools.elasticjob.jobs.SecondJob;

public class ElasticJobMain {
	private static final int ZOOKEEPER_PORT = 2181;
	private static final String ZOOKEEPER_CONNECTION_STRING = "localhost:" + ZOOKEEPER_PORT;
	private static final String JOB_NAMESPACE = "elastic-job-example-dataanalysis";
	
	private static final String EVENT_RDB_STORAGE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String EVENT_RDB_STORAGE_URL = "jdbc:mysql://192.168.125.7:3306/elastic_job_log";
    
    private static final String EVENT_RDB_STORAGE_USERNAME = "soho";
    
    private static final String EVENT_RDB_STORAGE_PASSWORD = "soho";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
		CoordinatorRegistryCenter regCenter = setUpRegistryCenter();
		JobEventConfiguration jobEventConfig = new JobEventRdbConfiguration(setUpEventTraceDataSource());
//      setUpSimpleJob(regCenter, jobEventConfig);
		setUpFirstJob(regCenter, jobEventConfig);
		setUpSecondJob(regCenter, jobEventConfig);*/
		
		new ClassPathXmlApplicationContext("classpath:elasticjob-context.xml");
	}

	
	private static CoordinatorRegistryCenter setUpRegistryCenter() {
        ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(ZOOKEEPER_CONNECTION_STRING, JOB_NAMESPACE);
        CoordinatorRegistryCenter result = new ZookeeperRegistryCenter(zkConfig);
        result.init();
        return result;
    }
	
	private static DataSource setUpEventTraceDataSource() {
        BasicDataSource result = new BasicDataSource();
        result.setDriverClassName(EVENT_RDB_STORAGE_DRIVER);
        result.setUrl(EVENT_RDB_STORAGE_URL);
        result.setUsername(EVENT_RDB_STORAGE_USERNAME);
        result.setPassword(EVENT_RDB_STORAGE_PASSWORD);
        return result;
    }
	
	private static void setUpFirstJob(final CoordinatorRegistryCenter regCenter, final JobEventConfiguration jobEventConfig) {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("firstJob", "0/10 * * * * ?", 1).shardingItemParameters("0=Beijing,1=Shanghai,2=Guangzhou,4=Hangzhou,5=Wuhan").build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, FirstJob.class.getCanonicalName());
        new JobScheduler(regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build(), jobEventConfig).init();
    }
	
	private static void setUpSecondJob(final CoordinatorRegistryCenter regCenter, final JobEventConfiguration jobEventConfig) {
        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("secondJob", "0/10 * * * * ?", 1).shardingItemParameters("0=Beijing,1=Shanghai,2=Guangzhou,4=Hangzhou,5=Wuhan").build();
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(coreConfig, SecondJob.class.getCanonicalName());
        new JobScheduler(regCenter, LiteJobConfiguration.newBuilder(simpleJobConfig).build(), jobEventConfig).init();
    }
}
