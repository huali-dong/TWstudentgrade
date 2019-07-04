package com.tw;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MainApplication {
    public static void main(String[] args) {
        printTips();
        boolean isContinue = true;
        Map<String, Student> students = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        while (isContinue) {
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
                    addStudent(scanner, students);
                    break;
                case 2:
                    System.out.println("请输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                    printStudentScore(scanner, students);
                    break;
                case 3:
                    isContinue = false;
                    break;
                default:
                    System.out.println("请输入正确的序号！");
                    break;
            }
        }
    }

    private static void printStudentScore(Scanner scanner, Map<String, Student> students) {
        while (true) {
            String inputString = scanner.next();
            String regex = "^(\\d+，)*(\\d+)$";
            boolean isMatch = inputString.matches(regex);
            if (isMatch) {
                String[] stuIds = inputString.split("，");
                System.out.print("成绩单\n" +
                        "姓名|数学|语文|英语|编程|平均分|总分\n" +
                        "========================\n");
                for (String stuId : stuIds) {
                    Student student = students.get(stuId);
                    if (student != null) {
                        System.out.print(student.getName()+"|");
                        Map<String, Float> subjects = student.getCourses();
                        System.out.print(subjects.get("数学") + "|");
                        System.out.print(subjects.get("语文") + "|");
                        System.out.print(subjects.get("英语") + "|");
                        System.out.print(subjects.get("编程") + "|");
                        System.out.print(student.getAvgGrade() + "|");
                        System.out.print(student.getTotalGrade() + "\n");
                    }
                }
                printTips();
                break;
            } else {
                printGradeReportSyntaxError();
            }
        }
    }

    private static void printGradeReportSyntaxError() {
        System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
    }

    private static void addStudent(Scanner scanner, Map<String, Student> students) {
        while (true) {
            String studentMsg = scanner.next();
            String regex = "^[\\u4E00-\\u9FA5A-Za-z]{2,}，\\d+，([\\u4E00-\\u9FA5A-Za-z]{2,}：([0-9]{1,2}|100)，)*([\\u4E00-\\u9FA5A-Za-z]{2,}：([0-9]{1,2}|100))$";
            boolean isMatch = studentMsg.matches(regex);
            if (isMatch) {
                int firstIndex = studentMsg.indexOf("，");
                String studentName = studentMsg.substring(0, firstIndex);
                int secondIndex = studentMsg.indexOf("，", firstIndex + 1);
                String stuId = studentMsg.substring(firstIndex + 1, secondIndex);
                String[] coursesAndGrades = studentMsg.substring(secondIndex + 1).split("，");
                Map<String, Float> courses = new HashMap<>();
                float totalGrade = 0;
                for (String courseAndGrade : coursesAndGrades) {
                    String[] keyAndValue = courseAndGrade.split("：");
                    String courseName = keyAndValue[0];
                    float courseGrade = Float.parseFloat(keyAndValue[1]);
                    courses.put(courseName, courseGrade);
                    totalGrade = courseGrade + totalGrade;
                }
                Student student = new Student(stuId, studentName, courses, totalGrade);
                students.put(student.getsId(), student);
                System.out.println("学生" + studentName + "的成绩被添加");
                printTips();
                break;
            } else {
                printAddStudentSyntaxError();
            }
        }

    }

    private static void printAddStudentSyntaxError() {
        System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
    }

    private static void printTips() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出请输入你的选择（1～3）：");
    }
}
