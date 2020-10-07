package com.example.springbatch.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features")
public class CucumberTest {
}
