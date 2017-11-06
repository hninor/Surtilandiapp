package com.wimobile.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Utils {
	public static  File toJavaFilename(String targetPath, String javaPackage, String javaClassName) {
        String packageSubPath = targetPath + "/" + javaPackage.replace('.', '/');
        File packagePath = new File(packageSubPath);
        
        packagePath.mkdirs();
        File file = new File(packagePath, javaClassName + ".java");
        return file;
    }
	
	public static void buildFile(String targetPath, Configuration cfg, Map<String, Object> input, String templateName,String packageName, String className) {
		try {
			Template template = cfg.getTemplate(templateName);
			Writer consoleWriter = new OutputStreamWriter(System.out);
			template.process(input, consoleWriter);

			
			Writer fileWriter = new FileWriter(toJavaFilename(targetPath, packageName, className));
			try {
				template.process(input, fileWriter);
			} finally {
				fileWriter.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
