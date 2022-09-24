/**
 * 
 */
package test.com.juxtapose.example.ch04;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author bruce.liu(mailto:jxta.liu@gmail.com)
 * 2013-2-28下午08:34:48
 */
public class JobLaunchValidator {
	
	/**
	 * 일괄 처리 작업을 수행하세요. <br>
	 * @param jobPath	작업 설정 파일
	 * @param jobName	작업명
	 * @param builder	작업 매개 변수 생성자
	 */
	public static void executeJob(String jobPath, String jobName, JobParametersBuilder builder) {
		ApplicationContext context = new ClassPathXmlApplicationContext(jobPath);
		JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean(jobName);
		try {
			JobExecution result = launcher.run(job, builder.toJobParameters());
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void executeJobValidatorOK(){
		executeJob("ch04/job/job-validator.xml", "billJob",
				new JobParametersBuilder().addDate("date", new Date()));
	}
	
	public static void executeJobValidatorError(){
		executeJob("ch04/job/job-validator.xml", "billJob",
				new JobParametersBuilder().addDate("date", new Date())
					.addString("test", "test parameter not allowed."));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		executeJobValidatorOK();
		// executeJobValidatorError();
	}
}
