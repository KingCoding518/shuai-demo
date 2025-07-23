package com.shuai.test;

/**
 * @Author: KingCoding
 * @Date: 2025/6/20
 * @Description:
 */

public class TestJava17 {

    public static void main(String[] args) {
        System.out.println(getHtmlJDK8());

        System.out.println("===================");

        System.out.println(getHtmlJDK17());
    }


    /**
     * 使用JDK8返回HTML文本
     *
     * @return 返回HTML文本
     */
    public static final String getHtmlJDK8() {
        return "<html>\n" +
                " <body>\n" +
                " <p>Hello, world</p>\n" +
                " </body>\n" +
                "</html>";
    }


    /**
     * 使用JDK17返回HTML文本
     * @return 返回HTML文本
     */
    public static final String getHtmlJDK17() {
        return """
        <html>
        <body>
        <p>Hello, world</p>
        </body>
        </html>
        """+ """
                你的爱想火苗
        把我的新燃烧""";
    }

}
