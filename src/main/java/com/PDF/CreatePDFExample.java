package com.PDF;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CreatePDFExample {
    public static void main(String[] args) {
        String textContent = "Hello, World!"; // 假设从用户获取到的文字内容

        String title = "Title"; // 标题文本
        int num = 4; // 列数

        try {
            PDDocument document = new PDDocument();

            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // 设置标题字体和位置
            PDType0Font font = PDType0Font.load(document, new File("D:\\Workspaces\\Eclipse\\PrimaryChinese\\src\\main\\webapp\\css\\font\\FangZhengKaiTiJianTi-1.ttf"));
            contentStream.setFont(font, 16);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700); // 根据需求设置标题位置（居中）
            contentStream.showText(title);
            contentStream.endText();

            // 绘制表格
            drawTable(contentStream, 50, 650, num);

            contentStream.close();

            document.save("D:\\output.pdf"); // 设置生成的 PDF 文件名和路径
            document.close();

            System.out.println("PDF 文件已创建并保存成功。");
        } catch (IOException e) {
            System.err.println("创建 PDF 文件时出现错误：" + e.getMessage());
        }
    }

    private static void drawTable(PDPageContentStream contentStream, float x, float y, int numColumns) throws IOException {
        float tableWidth = 500; // 表格宽度
        float columnWidth = tableWidth / numColumns; // 列宽度

        // 设置表头字体和位置
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
        float tableHeight = 20; // 表头高度

        // 绘制表头单元格
        float nextX = x;
        float nextY = y;
        drawCell(contentStream, nextX, nextY, columnWidth, tableHeight, "Prob");
        nextX += columnWidth;
        drawCell(contentStream, nextX, nextY, columnWidth, tableHeight, "Score");

        // 可以根据需要添加更多表头内容

        contentStream.setLineWidth(1); // 设置表格线宽度

        for (int i = 0; i <= numColumns; i++) {
            // 绘制垂直表格线
            contentStream.moveTo(x + i * columnWidth, y);
            contentStream.lineTo(x + i * columnWidth, y - 50); // 设置表格高度
            contentStream.stroke();
        }
    }

    private static void drawCell(PDPageContentStream contentStream, float x, float y, float width, float height,
                                 String text) throws IOException {
        final int fontSize = 12; // 字体大小
        final float cellMargin = 5; // 单元格内边距

        contentStream.setFont(PDType1Font.HELVETICA, fontSize);
        contentStream.beginText();
        contentStream.newLineAtOffset(x + cellMargin, y - cellMargin - height);
        contentStream.showText(text);
        contentStream.endText();
    }
}
