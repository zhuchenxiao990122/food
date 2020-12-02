package com.xw.project.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aspose.words.FontSettings;
import org.aspectj.weaver.ast.Test;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

/**
 * java将word转换为pdf
 *
 * @author 喻礼
 */
public class Word2Pdf {

    public static boolean getLicense() {

        boolean result = false;

        try {

            InputStream is = Test.class.getClassLoader().getResourceAsStream("license.xml");

            com.aspose.words.License aposeLic = new com.aspose.words.License();

            aposeLic.setLicense(is);

            result = true;
            is.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return result;

    }

    public static void word2pdf(String Address, String outPath) {

        if (!getLicense()) {

            return;

        }

        try {

            File file = new File(outPath); // 新建一个空白pdf文档

            FileOutputStream os = new FileOutputStream(file);

            Document doc = new Document(Address); // Address是将要被转化的word文档

          // FontSettings.setFontsFolder("/home/zdrcsvc/service/fonts", false);

            doc.save(os, SaveFormat.PDF);

            os.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
public static void main(String[] args){
    word2pdf("C:\\Users\\admin\\Desktop\\瑞士中华文化促进会（Swiss Chinese Culture Promotion Society)_202003120008.docx","C:\\Users\\admin\\Desktop\\捐赠意向函4.pdf");
}
}
