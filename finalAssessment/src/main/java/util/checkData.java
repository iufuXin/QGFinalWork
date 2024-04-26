package util;

import pojo.Chapter;

import java.util.ArrayList;
import java.util.Scanner;

public class checkData {
    //用于检验数据的方法

    /**
     * 检验电子邮箱
     *
     */
    public static boolean checkEmail(String email){
        if(email.matches("\\w{2,}@\\w{2,20}(\\.\\w{2,10}){1,2}")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 检验qq
     */
    public static boolean checkQQ(String qq){
        return qq != null && qq.matches("[1-9]\\d{5,19}");
    }
    /**
     * 检验密码
     */
    public static boolean checkPassword(String password){
        return password != null && password.length() >= 8;
    }

    /**
     * 通过传入学生答案比对获得正确率。并默认第一个为单选题的正确率，第二个为判断题的正确率
     * @param studentSingleTopicAnswer
     * @param studentJudgmentAnswer
     * @param courseIDChapterNumber
     * @return
     */
    public static ArrayList<Double> checkCorrectRate(String studentSingleTopicAnswer, String studentJudgmentAnswer, String courseIDChapterNumber){
        ArrayList<Double> doubles = new ArrayList<>();

        //获取章节信息
        Chapter chapter;
        chapter = SearchData.searchChapterBycourse_idAndchapterNumber(courseIDChapterNumber);

        int right = 0;

        //拿到正确答案跟学生答案比对
        for (int i = 0; i < Math.min(chapter.getSingleTopicAnswer().length(),studentSingleTopicAnswer.length()); i++) {
            //遍历答案，比对
            if (studentSingleTopicAnswer.charAt(i) == chapter.getSingleTopicAnswer().charAt(i)){
                right++;
            }
        }
        doubles.add(right * 1.0/(chapter.getSingleTopicAnswer().length() * 1.0));
        right = 0;//初始化
        for (int i = 0; i < Math.min(chapter.getJudgmentAnswer().length(),studentJudgmentAnswer.length()); i++) {
            //遍历答案，比对
            if (studentJudgmentAnswer.charAt(i) == chapter.getJudgmentAnswer().charAt(i)){
                right++;
            }
        }
        doubles.add(right * 1.0/(chapter.getJudgmentAnswer().length() * 1.0));

        return doubles;
    }

}
