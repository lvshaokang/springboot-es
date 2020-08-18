package com.lsk.es.example01.util;

import com.lsk.es.example01.model.WordDoc;
import org.apache.poi.POIXMLProperties;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * TODO
 *
 * @author lsk
 * @class_name WordUtils
 * @date 2020-06-28
 */
public class WordUtils {

    public static WordDoc parseWord(String path) {
        File file = new File(path);
        String fileName = file.getName();

        InputStream inputStream = null;

        WordDoc wordDoc = null;

        try {
            inputStream = new FileInputStream(file);

            if (fileName.endsWith(".doc")) {
                HWPFDocument hwpfDocument = new HWPFDocument(inputStream);
                WordExtractor wordExtractor = new WordExtractor(hwpfDocument);

                SummaryInformation summaryInformation = wordExtractor.getDocument().getSummaryInformation();
                String creator = summaryInformation.getAuthor();
                String dateStr = DateUtils.dateToString(summaryInformation.getCreateDateTime());
                String content = wordExtractor.getText();

                wordDoc = new WordDoc().setFileName(fileName).setCreated(dateStr)
                        .setCreator(creator).setContent(content);

                return wordDoc;
            }

            if (fileName.endsWith(".docx")) {
                XWPFDocument xwpfDocument = new XWPFDocument(inputStream);
                XWPFWordExtractor xwpfWordExtractor = new XWPFWordExtractor(xwpfDocument);

                POIXMLProperties.CoreProperties properties = xwpfWordExtractor.getCoreProperties();
                String creator = properties.getCreator();
                String dateStr = DateUtils.dateToString(properties.getCreated());
                String content = xwpfWordExtractor.getText();

                wordDoc = new WordDoc().setFileName(fileName).setCreated(dateStr)
                        .setCreator(creator).setContent(content);

                return wordDoc;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return wordDoc;
    }

    public static void main(String[] args) {
        WordDoc wordDoc = parseWord("C:\\Users\\Red\\Desktop\\数据中台业务需求文档 -V1.2.docx");

        System.out.println(wordDoc.getContent().trim());
    }

}


