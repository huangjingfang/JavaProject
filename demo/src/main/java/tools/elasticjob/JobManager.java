package tools.elasticjob;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import com.dangdang.ddframe.job.api.ShardingContext;

import tools.elasticjob.annotation.DependOn;
import tools.elasticjob.annotation.JobName;
import tools.elasticjob.struct.LinkedGraph;

public class JobManager {
	/*管理job名与job class的关系*/
	private Map<String, String> nameClassMap;
	
	/*管理job之间的依赖*/
	private LinkedGraph<String> graph;

	public JobManager() {
		// TODO Auto-generated constructor stub
		graph = new LinkedGraph<String>();
	}
	public Map<String, String> getNameClassMap() {
		return nameClassMap;
	}

	public void setNameClassMap(Map<String, String> nameClassMap) {
		this.nameClassMap = nameClassMap;
		Iterator<String> iterator = this.nameClassMap.keySet().iterator();
		/*构建任务节点*/
		while(iterator.hasNext()) {
			String name = iterator.next();
			try {
				graph.add(name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/**构建任务节点依赖*/
		Iterator<String> it = this.nameClassMap.keySet().iterator();
		while(it.hasNext()) {
			String jobName = it.next();
			String jobClass = this.nameClassMap.get(jobName);
			try {
				@SuppressWarnings("rawtypes")
				Class clazz = Class.forName(jobClass);
				@SuppressWarnings("unchecked")
				Method execute = clazz.getMethod("execute", ShardingContext.class);
				if(execute==null) {
					continue;
				}else {
					JobName name = execute.getAnnotation(JobName.class);
					DependOn depend = execute.getAnnotation(DependOn.class);
					if(depend==null) {
						continue;
					}else{
						String[] deps = depend.depend().split(",");
						for(String dep:deps) {
							graph.connect(dep, name.name());
						}
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getClass(String name) {
		return nameClassMap.get(name);
	}
	
	public LinkedGraph<String> getGraph(){
		return graph;
	}
}
