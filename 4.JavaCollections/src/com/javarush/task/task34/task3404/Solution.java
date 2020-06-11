package com.javarush.task.task34.task3404;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Рекурсия для мат. выражения
*/
public class Solution {
    public void recurse(final String expression, int countOperation) {
        String temp = expression;

        DecimalFormat format = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = format.getDecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        format.setDecimalFormatSymbols(dfs);

        if (countOperation == 0) {
            temp = temp.replaceAll("\\s+", "").toLowerCase();
            Pattern pattern1 = Pattern.compile("(sin|cos|tan|[\\^*/+-])");
            Matcher matcher1 = pattern1.matcher(temp);
            while (matcher1.find()) {
                countOperation++;
            }
        }

        if (temp.matches("(\\(-?\\d+\\.?\\d*\\))")){
            temp = temp.substring(1, temp.length()-1);
        }

        if (temp.matches("(-?\\d+\\.?\\d*)")) {
            if (temp.matches("-0")){
                temp = temp.substring(1);
            }
            System.out.printf("%s %d%n", format.format(Double.parseDouble(temp)), countOperation);
            return;
        }

        if (!temp.contains("(")){
            temp = "(" + temp + ")";
        }

        Pattern p = Pattern.compile("(?<=[+-])(\\(([-+]?\\d+\\.?\\d*)\\))");
        Matcher m = p.matcher(temp);
        while (m.find()){
            temp = temp.replace(m.group(), m.group(2));
        }
        temp = temp.replaceAll("(--)|(\\+\\+)", "+");
        temp = temp.replaceAll("(-\\+)|(\\+-)", "-");

        p = Pattern.compile("\\((\\(.+\\))\\)");
        m = p.matcher(temp);
        while (m.find()){
            temp = temp.substring(0, m.start()) + m.group(1) + temp.substring(m.end());
            m = p.matcher(temp);
        }

        p = Pattern.compile("(?:sin|cos|tan)\\((-?\\d+\\.?\\d*)\\)");
        m = p.matcher(temp);
        while (m.find()){
            String group = m.group(1);
            double d = Double.parseDouble(m.group(1)) * Math.PI / 180;
            switch (m.group().substring(0,3)){
                case "sin": d = Math.sin(d); break;
                case "cos": d = Math.cos(d); break;
                default: d = Math.tan(d);
            }
            String result = d < 0 ? "(" + format.format(d) + ")" : format.format(d);
            temp = temp.substring(0, m.start()) + result + temp.substring(m.end());
            m = p.matcher(temp);
        }

        p = Pattern.compile("((\\((-?\\d+\\.?\\d*)\\))|(\\d+\\.?\\d*))(\\^)((\\((-?\\d+\\.?\\d*)\\))|(\\d+\\.?\\d*))");
        m = p.matcher(temp);
        while (m.find()){
            double d1 = !m.group(1).contains("(") ? Double.parseDouble(m.group(4)) : Double.parseDouble(m.group(3));
            double d2 = !m.group(6).contains("(") ? Double.parseDouble(m.group(9)) : Double.parseDouble(m.group(8));
            double d = Math.pow(d1, d2);
            String result = d < 0 ? "(" + format.format(d) + ")" : format.format(d);
            temp = temp.substring(0, m.start()) + result + temp.substring(m.end());
            m = p.matcher(temp);
        }

        p = Pattern.compile("((\\((-?\\d+\\.?\\d*)\\))|(\\d+\\.?\\d*))([*/])((\\((-?\\d+\\.?\\d*)\\))|(\\d+\\.?\\d*))");
        m = p.matcher(temp);
        while (m.find()){
            double d1 = !m.group(1).contains("(") ? Double.parseDouble(m.group(4)) : Double.parseDouble(m.group(3));
            double d2 = !m.group(6).contains("(") ? Double.parseDouble(m.group(9)) : Double.parseDouble(m.group(8));
            double d = m.group().contains("*") ? d1 * d2 : d1 / d2;
            String result = d < 0 ? "(" + format.format(d) + ")" : format.format(d);
            temp = temp.substring(0, m.start()) + result + temp.substring(m.end());
            m = p.matcher(temp);
        }

        p = Pattern.compile("(?:sin|cos|tan)*(\\(([^()]+)\\))");
        m = p.matcher(temp);
        while (m.find()){
            Pattern p2 = Pattern.compile("-?\\d+\\.?\\d*");
            Matcher m2 = p2.matcher(m.group(2));
            double d = 0;
            while (m2.find()){
                d += Double.parseDouble(m2.group());
            }

            boolean isTrigonometric = false;
            String group = m.group();
            if (group.startsWith("sin") || group.startsWith("cos") || group.startsWith("tan")){
                isTrigonometric = true;
            }
            String result = d < 0 || isTrigonometric ? "(" + format.format(d) + ")" : format.format(d);
            temp = temp.replace(m.group(1), result);
        }
        recurse(temp, countOperation);
    }

    public Solution() {
        //don't delete
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); //expected output 0.5 6

        String s = "-2-(-2-1-(-2)-(-2)-(-2-2-(-2)-2)-2-2)";
        System.out.print(s + " expected output -3 16 actually ");
        solution.recurse(s, 0);
        s = "tan(2025 ^ 0.5)";
        System.out.print(s + " expected output 1 2 actually ");
        solution.recurse(s, 0);
        s = "sin(30*sin(-9 * 10))";
        System.out.print(s + " expected output -0.5 5 actually ");
        solution.recurse(s, 0);
        s = "sin(sin(sin(tan(30))))";
        System.out.print(s + " expected output 0 4 actually ");
        solution.recurse(s, 0);
        s = "sin(sin(sin(tan(30))))+0.000000034";
        System.out.print(s + " expected output 0 5 actually ");
        solution.recurse(s, 0);
        s = "-2^(-2)";
        System.out.print(s + " expected output -0.25 3 actually ");
        solution.recurse(s, 0);
        s = "tan(45)";
        System.out.print(s + " expected output 1 1 actually ");
        solution.recurse(s, 0);
        s = "tan(-45)";
        System.out.print(s + " expected output -1 2 actually ");
        solution.recurse(s, 0);
        s = "0.305";
        System.out.print(s + " expected output 0.3 0 actually ");
        solution.recurse(s, 0);
        s = "0.3051";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recurse(s, 0);
        s = "(0.3051)";
        System.out.print(s + " expected output 0.31 0 actually ");
        solution.recurse(s, 0);
        s = "1+(1+(1+1)*(1+1))*(1+1)+1";
        System.out.print(s + " expected output 12 8 actually ");
        solution.recurse(s, 0);
        s = "tan(44+sin(89-cos(180)^2))";
        System.out.print(s + " expected output 1 6 actually ");
        solution.recurse(s, 0);
        s = "-2+(-2+(-2)-2*(2+2))";
        System.out.print(s + " expected output -14 8 actually ");
        solution.recurse(s, 0);
        s = "sin(80+(2+(1+1))*(1+1)+2)";
        System.out.print(s + " expected output 1 7 actually ");
        solution.recurse(s, 0);
        s = "1+4/2/2+2^2+2*2-2^(2-1+1)";
        System.out.print(s + " expected output 6 11 actually ");
        solution.recurse(s, 0);
        s = "10-2^(2-1+1)";
        System.out.print(s + " expected output 6 4 actually ");
        solution.recurse(s, 0);
        s = "2^10+2^(5+5)";
        System.out.print(s + " expected output 2048 4 actually ");
        solution.recurse(s, 0);
        s = "1.01+(2.02-1+1/0.5*1.02)/0.1+0.25+41.1";
        System.out.print(s + " expected output 72.96 8 actually ");
        solution.recurse(s, 0);
        s = "0.000025+0.000012";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recurse(s, 0);
        s = "cos(3 + 19*3)";
        System.out.print(s + " expected output 0.5 3 actually ");
        solution.recurse(s, 0);
        s = "2*(589+((2454*0.1548/0.01*(-2+9^2))+((25*123.12+45877*25)+25))-547)";
        System.out.print(s + " expected output 8302244 14 actually ");
        solution.recurse(s, 0);
        s = "(-1 + (-2))";
        System.out.print(s + " expected output -3 3 actually ");
        solution.recurse(s, 0);
        s = "-sin(2*(-5+1.5*4)+28)";
        System.out.print(s + " expected output -0.5 7 actually ");
        solution.recurse(s, 0);
        s = "sin(100)-sin(100)";
        System.out.print(s + " expected output 0 3 actually ");
        solution.recurse(s, 0);
        s = "-(-22+22*2)";
        System.out.print(s + " expected output -22 4 actually ");
        solution.recurse(s, 0);
        s = "-(-2^(-2))+2+(-(-2^(-2)))";
        System.out.print(s + " expected output 2.5 10 actually ");
        solution.recurse(s, 0);
        s = "(-2)*(-2)";
        System.out.print(s + " expected output 4 3 actually ");
        solution.recurse(s, 0);
        s = "(-2)/(-2)";
        System.out.print(s + " expected output 1 3 actually ");
        solution.recurse(s, 0);
        s = "sin(-30)";
        System.out.print(s + " expected output -0.5 2 actually ");
        solution.recurse(s, 0);
        s = "cos(-30)";
        System.out.print(s + " expected output 0.87 2 actually ");
        solution.recurse(s, 0);
        s = "tan(-30)";
        System.out.print(s + " expected output -0.58 2 actually ");
        solution.recurse(s, 0);
        s = "2+8*(9/4-1.5)^(1+1)";
        System.out.print(s + " expected output 6.48 6 actually ");
        solution.recurse(s, 0);
        s = "0.005 ";
        System.out.print(s + " expected output 0.01 0 actually ");
        solution.recurse(s, 0);
        s = "0.0049 ";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recurse(s, 0);
        s = "0+0.304";
        System.out.print(s + " expected output 0.3 1 actually ");
        solution.recurse(s, 0);
        s = "0.000000000001";
        System.out.print(s + " expected output 0 0 actually ");
        solution.recurse(s, 0);
        s = "-0";
        System.out.print(s + " expected output 0 1 actually ");
        solution.recurse(s, 0);
    }

}
