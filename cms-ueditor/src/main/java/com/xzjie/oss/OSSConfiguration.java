package com.xzjie.oss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.annotation.Resource;

public class OSSConfiguration {

	private static String FILE_NAME = "OSS.properties";
	private static Properties prop = new Properties();
	static {
		// String
		// path=OSSConfiguration.class.getClassLoader().getResource("").getPath();
		// System.out.println(path+FILE_NAME);

		FileInputStream fis;
		try {
			fis = new FileInputStream(FILE_NAME);
			prop.load(fis);
			// System.out.println(path+FILE_NAME);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * if(properties==null){ Resource resource = new
		 * ClassPathResource(fileName); try { this.properties =
		 * PropertiesLoaderUtils.loadProperties(resource); }catch (IOException
		 * e){ log.error("property file not found ",e); } }
		 */
	}

	public static void main(String[] args) {
		OSSConfiguration conf = new OSSConfiguration();
	}
}
