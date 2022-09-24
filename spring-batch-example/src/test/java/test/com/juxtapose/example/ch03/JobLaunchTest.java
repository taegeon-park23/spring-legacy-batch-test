package test.com.juxtapose.example.ch03;


import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JobLaunchTest {
	private JobLauncher jobLauncher;
	private Job job;

	@BeforeEach
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"ch03/job/job.xml");
		jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		job = (Job) context.getBean("billJob");
	}

	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	public void billJob() throws Exception {
		JobExecution result = jobLauncher.run(job, (new JobParametersBuilder()
				.addDate("date", new Date())).toJobParameters());       
		System.out.println(result.toString());     
	}
}
