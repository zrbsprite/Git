package com.ldyt.boot.generator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ShellRunner;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {

	/**
	 * <b>描述：</b>使用原始mybatisgenerator<br>
	 * <b>作者:</b> ZRB
	 * <b>修改日期：</b>2017年4月12日上午10:36:11
	 */
	@Test
	public void generate(){
		ShellRunner.main(new String[]{"-configfile", "E:\\项目\\Git\\boot\\target\\test-classes\\com\\ldyt\\boot\\generator\\generatorConfig.xml","-overwrite"}); 
	}
	
	/**
	 * <b>描述：</b>使用通用mapper的专属生成器<br>
	 * <b>作者:</b> ZRB
	 * <b>修改日期：</b>2017年4月12日上午10:36:43
	 * @throws InvalidConfigurationException
	 * @throws IOException
	 * @throws XMLParserException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	@Test
	public void generateWithCommonMapper() throws InvalidConfigurationException, IOException, XMLParserException, SQLException, InterruptedException{
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		ConfigurationParser configurationParser = new ConfigurationParser(warnings);
		Configuration config = configurationParser.parseConfiguration(Generator.class.getResourceAsStream("generatorConfig.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
	}
}
