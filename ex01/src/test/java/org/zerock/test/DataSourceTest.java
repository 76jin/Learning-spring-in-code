package org.zerock.test;

import static org.junit.Assert.*;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class DataSourceTest {

	@Inject
	private DataSource ds;
	
	@Test
	public void testDI() throws Exception {
		System.out.println("DI dataSource:" + ds.toString());
	}
	
	@Test
	public void testConnection() throws Exception {
		try (Connection con = ds.getConnection()) {
			System.out.println("connection:" + con.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
