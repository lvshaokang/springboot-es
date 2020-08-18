//package com.lsk.es.util;
//
//import org.apache.poi.POIXMLProperties;
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
//
//import java.io.*;
//import java.net.URI;
//import java.nio.file.Path;
//
///**
// * TODO:
// *
// * @author red
// * @class_name WordUtils
// * @date 2020-06-27
// */
//public class WordUtils {
//
//    // https://blog.csdn.net/zwq_zwq_zwq/article/details/80638079
//    public static void  parseWord(String path) throws IOException {
//        File file = new File(path);
//
//        String docName = file.getName();
//
//        if (docName.endsWith(".doc")) {
//            HWPFDocument doc = new HWPFDocument(new FileInputStream(file));
//            WordExtractor extractor = new WordExtractor(doc);
//
//            System.out.println("-----");
//        }
//
//        if (docName.endsWith(".docx")) {
//            XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(file));
//            XWPFWordExtractor extractor = new XWPFWordExtractor(xwpfDocument);
//            // 获取文档内容
//            String text = extractor.getText();
//            System.out.println("-----");
//
//            POIXMLProperties.CoreProperties coreProperties = extractor.getCoreProperties();
//
//            System.out.println(docName+"\t"+coreProperties.getCreated()+"\t"+coreProperties.getCreator());
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        parseWord("E:\\Dell\\Desktop\\高级班总结-Red.docx");
//
//        // https://www.cnblogs.com/estellez/p/4091429.html
//    }
//
//
//}
