package com.juxtapose.example.ch11.listener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.core.SkipListener;
import org.springframework.jdbc.core.JdbcTemplate;

import com.juxtapose.example.ch11.multithread.CreditBill;

public class FailBillInsertLogListener implements SkipListener<CreditBill, CreditBill> {
    private JdbcTemplate jdbcTemplate;

	@Override
    public void onSkipInWrite(CreditBill item, Throwable t) {
        try {
            if (t instanceof RuntimeException) {
                jdbcTemplate.update("insert into skipbills "
                    + "(line,content) values (?,?)",
                    t.getMessage(),
                    (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

	public void onSkipInProcess(CreditBill item, Throwable t) {
		try {
            if (t instanceof RuntimeException) {
                jdbcTemplate.update("insert into skipbills "
                    + "(line,content) values (?,?)",
                    t.getMessage(),
                    (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

    @Override
    public void onSkipInRead(Throwable t) {
        try {
            if (t instanceof RuntimeException) {
                jdbcTemplate.update("insert into skipbills "
                    + "(line,content) values (?,?)",
                    t.getMessage(),
                    (LocalDateTime.now()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
