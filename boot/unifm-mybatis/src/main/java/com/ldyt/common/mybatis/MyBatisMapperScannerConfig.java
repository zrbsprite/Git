package com.ldyt.common.mybatis;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：MyBatis扫描接口,适用于方式二<br>
 * 作者：ZRB <br>
 * 修改日期：2017年4月11日下午4:24:57 <br>
 * 备注：这个类执行的比较早，由于sqlSessionFactory还不存在，后续执行出错。
 */
@Configuration
// 由于MapperScannerConfigurer执行的比较早，所以必须有下面的注解
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {

	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		mapperScannerConfigurer.setBasePackage("com.ldyt.boot.dao");
		return mapperScannerConfigurer;
	}
}
