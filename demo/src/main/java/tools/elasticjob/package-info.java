/**
 * 
 * 该包主要介绍的是在elasticjob的分布式任务系统中增加任务依赖的方式
 * 
 * 具体的实现原理基于Spring AOP和zookeeper
 * 最终的效果是只需要在任务的execute方法前加注解@JobName和@DependOn来标志任务名和依赖，之后再AOP中解析注解信息，并使用zookeeper同步不同的任务
 */
/**
 * @author h15039
 *
 */
package tools.elasticjob;