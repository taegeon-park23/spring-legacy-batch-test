/**
 * 
 */
package test.com.juxtapose.example.ch04;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author bruce.liu(mailto:jxta.liu@gmail.com)
 * 2013-2-28下午08:34:48
 */
public class JobLaunchStop {
	
	/**
	 * 일괄 처리 작업을 수행하세요. <br>
	 * @param jobPath	작업 설정 파일
	 * @param jobName	작업명
	 * @param builder	작업 매개 변수 생성자
	 */
	public static void executeJobAndStop(String jobPath, String jobName, JobParametersBuilder builder) {
		ApplicationContext context = new ClassPathXmlApplicationContext(jobPath);
		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncherAsyn");
		JobOperator jobOperator = (JobOperator) context.getBean("jobOperator");
		Job job = (Job) context.getBean(jobName);
		try {
			launcher.run(job, builder.toJobParameters());
			Set<Long> runningExecutions = jobOperator.getRunningExecutions(jobName);
			Iterator<Long> iterator = runningExecutions.iterator();
			while(iterator.hasNext()){
				boolean sendMessage = jobOperator.stop(iterator.next());
				System.out.println("sendMessage:" + sendMessage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeJobAndStop("ch04/job/job-stop.xml", "chunkJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
