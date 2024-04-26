package Test;

import pojo.Teacher;

public class TestPojo {
    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        teacher.setId("3123");
        teacher.setEmail("@email4680");
        teacher.setQq("4680");
        teacher.setDesc("没有作业");
        teacher.setName("liufu");
        teacher.setPassword("123456");
        System.out.println(teacher.toString());



    }


}
