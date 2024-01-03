package com.servlet;

import com.Bean.Question;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dao.QuestionDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/PDFServlet")
public class PDFservlet extends HttpServlet {
    //服务器功能：传入一个标题和一个试题集合后，直接生成对应的PDF文件
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");//告知浏览器用UTF-8来解析

        QuestionDAO questionDAO = new QuestionDAO();

        System.out.println("开始执行");
        String JSONData = req.getParameter("IdArray");
        String exam_id = req.getParameter("exam_id");
        String title = req.getParameter("exam_name");

        System.out.println(JSONData);
        System.out.println(exam_id);
        System.out.println(title);
        List<Question> questionList = new ArrayList<>();
        List<Question> allQuestionList = new ArrayList<>();
        allQuestionList = questionDAO.getALLProb();
        //获取所有试题的列表
        Map<Integer, Question> map = new HashMap<>();
        for(Question question:allQuestionList){
            map.put(question.getId(),question);
        }
        JSONArray jsonArray = JSON.parseArray(JSONData);
        for(int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer id = jsonObject.getInteger("id");

            questionList.add(map.get(id));
        }
        //成功生成问题集合
        Init init = new Init(title,questionList);
        String latexCode = init.initCode();

        //System.out.println(latexCode);

        try {
            // 创建一个新的 .tex 文件并写入 LaTeX 代码
            String texFilePath = title+".tex";
            java.nio.file.Files.write(java.nio.file.Paths.get(texFilePath), latexCode.getBytes("UTF-8"));
            System.out.println(latexCode);
            String[] compileCommand = { "xelatex", "-interaction=nonstopmode", texFilePath };

            // 创建进程构建器
            ProcessBuilder processBuilder = new ProcessBuilder(compileCommand);

            // 启动进程
            Process process = processBuilder.start();

            // 读取输出信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 等待编译完成
            System.out.println("start");
            int exitCode = process.waitFor();
            System.out.println("finish");
            if (exitCode == 0) {
                System.out.println("LaTeX 编译成功！");
                String pdfFilePath = title+".pdf";
                String destinationPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0_Tomcat8\\webapps\\PrimaryChinese\\PDF"+title+".pdf";

                try {


                    // 将文件移动到指定位置
                    Path source = Path.of(pdfFilePath);
                    Path destination = Path.of(destinationPath);
                    java.nio.file.Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("PDF文件移动成功！");

                    String Path = "http://localhost/PrimaryChinese/PDF/"+title+".pdf"+"?timestamp=" + System.currentTimeMillis();
                    System.out.println(Path);
                    PrintWriter out = resp.getWriter();

                    out.print(Path);
                    //将PDF文件的存储路径告诉前端
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("PDF文件移动失败！");
                }
            } else {
                // 获取编译错误输出
                InputStream errorStream = process.getErrorStream();
                reader = new BufferedReader(new InputStreamReader(errorStream));
                while ((line = reader.readLine()) != null) {
                    System.err.println(line);
                }
                System.err.println("LaTeX 编译失败！");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void loadPdfFiles() {
        ServletContext context = getServletContext();
        String pdfDirectory = context.getRealPath("/PDF"); // 指定 PDF 文件夹的路径

        // 扫描指定目录下的 PDF 文件，并将其添加到可访问的资源列表中
        File[] files = new File(pdfDirectory).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".pdf");
            }
        });

        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String filePath = file.getAbsolutePath();
                context.setAttribute(fileName, filePath);
            }
        }
    }
}
class Init{
    String title = "";
    //试题列表
    java.util.List<Question>questionList;
    Init(String title,java.util.List<Question>questionList){
        this.title = title;
        this.questionList = questionList;
    }
    public String showQuestion(Question question){
        //传入一个问题对象，将其转化为一段可编译的latex代码

        String latexCode = "";
        latexCode = "\\question[10] "+question.getQuestion()+"\n" +
                "\n" +
                "\\begin{choices}\n" +
                "\\choice "+question.getOptionA()+"\n" +
                "\\choice "+question.getOptionB()+"\n" +
                "\\choice "+question.getOptionC()+"\n" +
                "\\choice "+question.getOptionD()+"\n" +
                "\\end{choices}\n" +
                "\n";
        return latexCode;
    }
    public String showQuestions(java.util.List<Question>questionList){
        String latexCode ="\\begin{questions}\n" +
                "\n";
        for(Question question:questionList){
            latexCode += showQuestion(question);
        }
        latexCode += "\\end{questions}\n" +
                "\n";
        return latexCode;
    }
    public String initCode(){
        String latexCode = "\\documentclass[addpoints,answers]{exam}\n" +
                "\n" +
                "\\usepackage{xeCJK}\n" +
                "\\usepackage{zhnumber}\n" +
                "\\usepackage{graphicx}\n" +
                "\\usepackage{hyperref}\n" +
                "\\usepackage{amsmath}\n" +
                "\\usepackage{booktabs}\n" +
                "\\usepackage{multicol}\n"+
                "\n" +
                "\\pagestyle{headandfoot}\n" +
                "\\firstpageheadrule\n" +
                "\\firstpageheader{小学语文}{}{"+title+"}\n" +
                "\\runningheader{小学语文}\n" +
                "{}\n" +
                "{"+title+"}\n" +
                "\\runningheadrule\n" +
                "\\firstpagefooter{}{第\\thepage\\ 页（共\\numpages 页）}{}\n" +
                "\\runningfooter{}{第\\thepage\\ 页（共\\numpages 页）}{}\n" +
                "\n" +
                "\n" +
                "\\pointname{ 分}\n" +
                "\\pointformat{（\\thepoints）}\n" +
                "\n" +
                "\\totalformat{共\\totalpoints 分}\n" +
                "\n" +
                "\\setlength\\linefillheight{.5in}\n" +
                "\n" +
                "\\renewcommand{\\solutiontitle}{\\noindent\\textbf{答：}}\n" +
                "\n" +
                "\n" +
                "\\renewcommand{\\thequestion}{\\zhnum{question}}\n" +
                "\\renewcommand{\\questionlabel}{\\thequestion .}\n" +
                "\\renewcommand{\\thepartno}{\\arabic{partno}}\n" +
                "\\renewcommand{\\partlabel}{\\thepartno .}\n" +
                "\n" +
                "\n" +
                "\\begin{document}\n" +
                "\\begin{center}\n" +
                "    \\Huge "+title+"\n" +
                "\\end{center}\n" +
                showQuestions(questionList) +
                "\\end{document}";
        return latexCode;
    }
}
