package com.openwjk.central.dao.plugins;

import com.openwjk.commons.utils.Constant;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author wangjunkai
 * @description
 * @date 2023/8/25 10:40
 */
public class FileExtPlugin extends PluginAdapter {
    private static String XMLFILE_POSTFIX = "Ext";
    private static String JAVAFILE_POTFIX = "Ext";
    private static String ANNOTATION_MAPPER = "org.apache.ibatis.annotations.Mapper";


    @Override
    public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {
        String[] splitFile = introspectedTable.getMyBatis3XmlMapperFileName().split("\\.");
        String fileNameExt = null;
        if (splitFile[0] != null) {
            fileNameExt = splitFile[0] + XMLFILE_POSTFIX + ".xml";
        }

        if (this.isExistExtFile(this.context.getSqlMapGeneratorConfiguration().getTargetProject(), introspectedTable.getMyBatis3XmlMapperPackage(), fileNameExt)) {
            return super.contextGenerateAdditionalXmlFiles(introspectedTable);
        } else {
            Document document = new Document("-//mybatis.org//DTD Mapper 3.0//EN", "http://mybatis.org/dtd/mybatis-3-mapper.dtd");
            XmlElement root = new XmlElement("mapper");
            document.setRootElement(root);
            String namespace = introspectedTable.getMyBatis3SqlMapNamespace() + XMLFILE_POSTFIX;
            root.addAttribute(new Attribute("namespace", namespace));
            GeneratedXmlFile gxf = new GeneratedXmlFile(document, fileNameExt, introspectedTable.getMyBatis3XmlMapperPackage(), this.context.getSqlMapGeneratorConfiguration().getTargetProject(), false, this.context.getXmlFormatter());
            List<GeneratedXmlFile> answer = new ArrayList(1);
            answer.add(gxf);
            return answer;
        }
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType() + JAVAFILE_POTFIX);
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        this.context.getCommentGenerator().addJavaFileComment(interfaze);
        FullyQualifiedJavaType baseInterfaze = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());
        interfaze.addSuperInterface(baseInterfaze);
        FullyQualifiedJavaType annotation = new FullyQualifiedJavaType(ANNOTATION_MAPPER);
        interfaze.addAnnotation("@Mapper");
        interfaze.addImportedType(annotation);
        GeneratedJavaFile generatedJavaFile = new GeneratedJavaFile(interfaze, this.context.getJavaModelGeneratorConfiguration().getTargetProject(), this.context.getProperty("javaFileEncoding"), this.context.getJavaFormatter());
        if (this.isExistExtFile(generatedJavaFile.getTargetProject(), generatedJavaFile.getTargetPackage(), generatedJavaFile.getFileName())) {
            return super.contextGenerateAdditionalJavaFiles(introspectedTable);
        } else {
            List<GeneratedJavaFile> generatedJavaFiles = new ArrayList(1);
            generatedJavaFile.getFileName();
            generatedJavaFiles.add(generatedJavaFile);
            return generatedJavaFiles;
        }
    }

    private boolean isExistExtFile(String targetProject, String targetPackage, String fileName) {
        File project = new File(targetProject);
        if (!project.isDirectory()) {
            return true;
        } else {
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(targetPackage, Constant.POINT);

            while(st.hasMoreTokens()) {
                sb.append(st.nextToken());
                sb.append(File.separatorChar);
            }

            File directory = new File(project, sb.toString());
            if (!directory.isDirectory()) {
                boolean rc = directory.mkdirs();
                if (!rc) {
                    return true;
                }
            }

            File testFile = new File(directory, fileName);
            return testFile.exists();
        }
    }

    @Override
    public boolean validate(List<String> list) {
        return true;
    }
}
