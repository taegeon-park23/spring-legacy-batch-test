package com.juxtapose.example.ch11.tasklet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;

import com.juxtapose.example.ch11.data.DataShareBean;
import com.juxtapose.example.ch11.multithread.CreditBill;

public class FailBillInesertLogTasklet implements Tasklet {

    JdbcTemplate jdbcTemplate = null;
    DataShareBean<CreditBill> dataShareBean;
    
	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {
		if(dataShareBean.getSize() > 0) {
            for(CreditBill creditBill : dataShareBean.getDataList()) {
                jdbcTemplate.update("insert into skipbills "
					+ "(line,content) values (?,?)",
					creditBill.getId(),
					(LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        }

		return RepeatStatus.FINISHED;
	}

    public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
    public DataShareBean<CreditBill> getDataShareBean() {
		return dataShareBean;
	}

	public void setDataShareBean(DataShareBean<CreditBill> dataShareBean) {
		this.dataShareBean = dataShareBean;
	}
}
