/**
 * 
 */
package test.com.juxtapose.example.ch04;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.juxtapose.example.ch04.stop.StopStepListener;

/**
 * 
 * @author bruce.liu(mailto:jxta.liu@gmail.com)
 * 2013-3-20下午10:35:56
 */
public class JobLaunchStopBusiness {
	
	/**
	 * 일괄 처리 작업을 수행하세요. <br>
	 * @param jobPath	작업 설정 파일
	 * @param jobName	작업명
	 * @param builder	작업 매개 변수 생성자
	 */
	public static void executeJobAndStop(String jobPath, String jobName, JobParametersBuilder builder) {
		ApplicationContext context = new ClassPathXmlApplicationContext(jobPath);
		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncherAsyn");
		StopStepListener<?> stopListener = (StopStepListener<?>) context.getBean("stopListener");
		Job job = (Job) context.getBean(jobName);
		try {
			launcher.run(job, builder.toJobParameters());
			stopListener.setStop(Boolean.TRUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeJobAndStop("ch04/job/job-stop.xml", "chunkBusinessJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
}
