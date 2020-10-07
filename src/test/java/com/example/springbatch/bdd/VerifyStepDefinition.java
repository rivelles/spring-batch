package com.example.springbatch.bdd;

import com.example.springbatch.SpringBatchApplication;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.After;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@Sql(config = @SqlConfig(dataSource = "appDataSource"),
        scripts = {"/schema.sql", "/scripts.sql"})
@CucumberContextConfiguration
@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = SpringBatchApplication.class, loader = SpringBootContextLoader.class)
@ActiveProfiles("test")
public class VerifyStepDefinition {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    private JobExecution jobExecution;

    @Given("a set of data is read in")
    public void loadData() throws Exception {
        jobExecution = jobLauncherTestUtils.launchJob();
    }

    @When("that data is processed")
    public void processData() {

    }

    @Then("output is writen")
    public void writeOutput() {
        Assert.assertEquals("COMPLETED", jobExecution.getExitStatus().getExitCode());

    }
}
