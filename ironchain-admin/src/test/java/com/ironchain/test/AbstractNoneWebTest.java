package com.ironchain.test;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

import com.ironchain.admin.config.DatabaseConfig;

@AutoConfigureDataJpa
@AutoConfigureJdbc
@SpringBootTest(classes=DatabaseConfig.class)
public class AbstractNoneWebTest extends AbstractTest{

}
