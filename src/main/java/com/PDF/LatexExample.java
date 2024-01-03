//package com.PDF;
//
//import com.Bean.Question;
//
//import java.awt.*;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.file.Path;
//import java.nio.file.StandardCopyOption;
//
//public class LatexExample {
//    private static Component Files;
//    public static void main(String[] args) {
//
//
//        Init init = new Init(title,questionList);
//
//        String latexCode = init.initCode();
//        //System.out.println(latexCode);
//        try {
//            // 创建一个新的 .tex 文件并写入 LaTeX 代码
//            String texFilePath = title+".tex";
//            java.nio.file.Files.write(java.nio.file.Paths.get(texFilePath), latexCode.getBytes("UTF-8"));
//
//            String[] compileCommand = { "xelatex", "-interaction=nonstopmode", texFilePath };
//
//            // 创建进程构建器
//            ProcessBuilder processBuilder = new ProcessBuilder(compileCommand);
//
//            // 启动进程
//            Process process = processBuilder.start();
//
//            // 读取输出信息
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            // 等待编译完成
//            System.out.println("start");
//            int exitCode = process.waitFor();
//            System.out.println("finish");
//            if (exitCode == 0) {
//                System.out.println("LaTeX 编译成功！");
//                String pdfFilePath = title+".pdf";
//                String destinationPath = "D:\\Workspaces\\Eclipse\\PrimaryChinese\\src\\main\\webapp\\PDF\\"+title+".pdf";
//
//                try {
//
//
//                    // 将文件移动到指定位置
//                    Path source = Path.of(pdfFilePath);
//                    Path destination = Path.of(destinationPath);
//                    java.nio.file.Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);
//
//                    System.out.println("PDF文件移动成功！");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    System.err.println("PDF文件移动失败！");
//                }
//            } else {
//                // 获取编译错误输出
//                InputStream errorStream = process.getErrorStream();
//                reader = new BufferedReader(new InputStreamReader(errorStream));
//                while ((line = reader.readLine()) != null) {
//                    System.err.println(line);
//                }
//                System.err.println("LaTeX 编译失败！");
//            }
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//public class Init{
//    String title = "";
//    //试题列表
//    java.util.List<Question>questionList;
//    Init(String title,java.util.List<Question>questionList){
//        this.title = title;
//        this.questionList = questionList;
//    }
//    public String showQuestion(Question question){
//        //传入一个问题对象，将其转化为一段可编译的latex代码
//
//        String latexCode = "";
//        latexCode = "\\question[10] "+question.getQuestion()+"\n" +
//                "\n" +
//                "\\begin{oneparchoices}\n" +
//                "\\choice "+question.getOptionA()+"\n" +
//                "\\choice "+question.getOptionB()+"\n" +
//                "\\choice "+question.getOptionC()+"\n" +
//                "\\choice "+question.getOptionD()+"\n" +
//                "\\end{oneparchoices}\n" +
//                "\n";
//        return latexCode;
//    }
//    public String showQuestions(java.util.List<Question>questionList){
//        String latexCode ="\\begin{questions}\n" +
//                "\n";
//        for(Question question:questionList){
//            latexCode += showQuestion(question);
//        }
//        latexCode += "\\end{questions}\n" +
//                "\n";
//        return latexCode;
//    }
//    public String initCode(){
//        String latexCode = "\\documentclass[addpoints,answers]{exam}\n" +
//                "\n" +
//                "\\usepackage{xeCJK}\n" +
//                "\\usepackage{zhnumber}\n" +
//                "\\usepackage{graphicx}\n" +
//                "\\usepackage{hyperref}\n" +
//                "\\usepackage{amsmath}\n" +
//                "\\usepackage{booktabs}\n" +
//                "\n" +
//                "\\pagestyle{headandfoot}\n" +
//                "\\firstpageheadrule\n" +
//                "\\firstpageheader{小学语文}{}{"+title+"}\n" +
//                "\\runningheader{小学语文}\n" +
//                "{}\n" +
//                "{"+title+"}\n" +
//                "\\runningheadrule\n" +
//                "\\firstpagefooter{}{第\\thepage\\ 页（共\\numpages 页）}{}\n" +
//                "\\runningfooter{}{第\\thepage\\ 页（共\\numpages 页）}{}\n" +
//                "\n" +
//                "\n" +
//                "\\pointname{ 分}\n" +
//                "\\pointformat{（\\thepoints）}\n" +
//                "\n" +
//                "\\totalformat{共\\totalpoints 分}\n" +
//                "\n" +
//                "\\setlength\\linefillheight{.5in}\n" +
//                "\n" +
//                "\\renewcommand{\\solutiontitle}{\\noindent\\textbf{答：}}\n" +
//                "\n" +
//                "\n" +
//                "\\renewcommand{\\thequestion}{\\zhnum{question}}\n" +
//                "\\renewcommand{\\questionlabel}{\\thequestion .}\n" +
//                "\\renewcommand{\\thepartno}{\\arabic{partno}}\n" +
//                "\\renewcommand{\\partlabel}{\\thepartno .}\n" +
//                "\n" +
//                "\n" +
//                "\\begin{document}\n" +
//                "\\begin{center}\n" +
//                "    \\Huge "+title+"\n" +
//                "\\end{center}\n" +
//                showQuestions(questionList) +
//                "\\end{document}";
//        return latexCode;
//    }
//}