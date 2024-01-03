//package com.servlet;
//
//
//import org.apache.commons.math3.linear.MatrixUtils;
//import org.apache.commons.math3.linear.RealMatrix;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.Arrays;
//
//@WebServlet("/recommendServlet")
//public class recommend extends HttpServlet {
//    //基于协同过滤算法的试题推荐系统
//    private static boolean contains(int[] arr, int num, int length) {
//        for (int i = 0; i < length; i++) {
//            if (arr[i] == num) {
//                return true;
//            }
//        }
//        return false;
//    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req,resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("text/html;charset=UTF-8");//告知浏览器用UTF-8来解析
//        double[][] userItemMatrix = {
//                {5, 0, 2, 1, 0, 0, 0, 3, 0, 4},
//                {0, 3, 0, 2, 1, 0, 4, 0, 0, 0},
//                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//                {0, 0, 1, 0, 2, 3, 0, 4, 0, 5},
//                {4, 0, 0, 3, 0, 1, 2, 5, 0, 0}
//        };
//
//
//
//
//
//
//
//        int targetUserIndex = 2;
//        int numRecommendations = 4;
//
////        double[][] userItemMatrix = {
////                {5, 0, 2, 1, 0, 0, 0, 3, 0, 4},
////                {0, 3, 0, 2, 1, 0, 4, 0, 0, 0},
////                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
////                {0, 0, 1, 0, 2, 3, 0, 4, 0, 5},
////                {4, 0, 0, 3, 0, 1, 2, 5, 0, 0}
////        };
//
//
//        targetUserIndex = Integer.parseInt(req.getParameter("user"));
//        //获取请求试题的用户的编号
//        numRecommendations = Integer.parseInt(req.getParameter("num"));
//        //获取其请求的试题数量
//
//        RealMatrix userMatrix = MatrixUtils.createRealMatrix(userItemMatrix);
//
//        // 计算用户之间的相似度矩阵（使用余弦相似度）
//        RealMatrix userSimilarityMatrix = userMatrix.multiply(userMatrix.transpose());
//        double[] norms = new double[userMatrix.getRowDimension()];
//        for (int i = 0; i < userMatrix.getRowDimension(); i++) {
//            norms[i] = Math.sqrt(userMatrix.getRowVector(i).getNorm());
//        }
//        RealMatrix normsMatrix = MatrixUtils.createColumnRealMatrix(norms);
//        RealMatrix normsTransposeMatrix = MatrixUtils.createRowRealMatrix(norms);
//        RealMatrix similarityDenominator = normsMatrix.multiply(normsTransposeMatrix);
//        userSimilarityMatrix = userSimilarityMatrix.scalarMultiply(1.0 / similarityDenominator.getEntry(targetUserIndex, targetUserIndex));
//
//        double[][] similarityData = userSimilarityMatrix.getData();
//
//        Integer[] similarUsersIndexes = new Integer[similarityData.length];
//        for (int i = 0; i < similarityData.length; i++) {
//            similarUsersIndexes[i] = i;
//        }
//        final int M =  targetUserIndex;
//        Arrays.sort(similarUsersIndexes, (a, b) -> Double.compare(similarityData[b][M], similarityData[a][M]));
//        int mostSimilarUserIndex = similarUsersIndexes[0];
//
//        double[] targetUserScores = userItemMatrix[targetUserIndex];
//        int[] unansweredItems = new int[targetUserScores.length];
//        int unansweredItemCount = 0;
//        for (int i = 0; i < targetUserScores.length; i++) {
//            if (targetUserScores[i] == 0) {
//                unansweredItems[unansweredItemCount++] = i;
//            }
//        }
//        unansweredItems = Arrays.copyOf(unansweredItems, unansweredItemCount);
//
//        double[] mostSimilarUserScores = userItemMatrix[mostSimilarUserIndex];
//        Integer[] recommendationsIndexes = new Integer[mostSimilarUserScores.length];
//        for (int i = 0; i < mostSimilarUserScores.length; i++) {
//            recommendationsIndexes[i] = i;
//        }
//        Arrays.sort(recommendationsIndexes, (a, b) -> Double.compare(mostSimilarUserScores[a], mostSimilarUserScores[b]));
//        int[] recommendations = new int[numRecommendations];
//        for (int i = 0; i < numRecommendations; i++) {
//            recommendations[i] = recommendationsIndexes[i];
//        }
//
//
//        PrintWriter out = resp.getWriter();
//        System.out.println("推荐给用户" + (targetUserIndex + 1) + "的" + numRecommendations + "道试题：");
//        out.println("推荐给用户" + (targetUserIndex + 1) + "的" + numRecommendations + "道试题：");
//        System.out.println(Arrays.toString(recommendations));
//    }
//}
