import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


@Title("Operand-allure-test")
public class TestMath {

    // в этом массиве будут хранится разложен
    private static final ArrayList<DecomposedLine> decomposedLines = new ArrayList<>();

    @BeforeClass
    public static void setUp() throws Exception {
        System.out.println("Загружаю файл...");
        File data_file = new File("src/test/resources/data.txt");

        BufferedReader bufferedReader = new BufferedReader(new FileReader(data_file));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] stringArray = line.split(";");

            // предполагаю что в текстовом файле всё действительно отформатированно
            // согласно виду operand1;operand2;operation;result
            // также что operand1, operand2 и result это числа(необязательно целые),
            // а в поле result могут быть только один символ из +-/*
            DecomposedLine decomposedLine = new DecomposedLine(
                    Float.valueOf(stringArray[0]),
                    Float.valueOf(stringArray[1]),
                    stringArray[2].charAt(0),
                    Float.valueOf(stringArray[3]));

            decomposedLines.add(decomposedLine);
        }

        // тут я решил немного почистить память
        decomposedLines.trimToSize();
        bufferedReader.close();

        System.out.println("Успешно.");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Завершаем тесты");
        decomposedLines.clear();
        System.out.println("Завершено");
    }

    @Step
    private static void testAdd(float operand1, float operand2, float expectedResult) {
        float actualresult = operand1 + operand2;
        System.out.println(operand1 + " + " + operand2 + " = " + actualresult);
        Assert.assertEquals(expectedResult, actualresult, 0.0);
    }

    @Step
    private static void testSub(float operand1, float operand2, float expectedResult) {
        float actualresult = operand1 - operand2;
        System.out.println(operand1 + " - " + operand2 + " = " + actualresult);
        Assert.assertEquals(expectedResult, actualresult, 0.0);
    }

    @Step
    private static void testDiv(float operand1, float operand2, float expectedResult) {
        float actualresult = operand1 / operand2;
        System.out.println(operand1 + " / " + operand2 + " = " + actualresult);
        Assert.assertEquals(expectedResult, actualresult, 0.0);
    }

    @Step
    private static void testMulty(float operand1, float operand2, float expectedResult) {
        float actualresult = operand1 * operand2;
        System.out.println(operand1 + " * " + operand2 + " = " + actualresult);
        Assert.assertEquals(expectedResult, actualresult, 0.0);
    }

    @Test
    public void testMath() {
        System.out.println("Начинаем тесты");

        for (DecomposedLine decomposedLine : decomposedLines) {
            switch (decomposedLine.getOperation()) {
                case ('+'):
                    testAdd(decomposedLine.getOperand1(), decomposedLine.getOperand2(), decomposedLine.getResult());
                    break;
                case ('-'):
                    testSub(decomposedLine.getOperand1(), decomposedLine.getOperand2(), decomposedLine.getResult());
                    break;
                case ('/'):
                    testDiv(decomposedLine.getOperand1(), decomposedLine.getOperand2(), decomposedLine.getResult());
                    break;
                case ('*'):
                    testMulty(decomposedLine.getOperand1(), decomposedLine.getOperand2(), decomposedLine.getResult());
                    break;
            }
        }
    }

    // вложенный класс в котором хранятся по отдельности операнды, результат и операция,
    // для удобства/наглядности работы со строкой из файла
    private static class DecomposedLine {
        private Float operand1;
        private Float operand2;
        private char operation;
        private Float result;

        DecomposedLine(Float operand1, Float operand2, char operation, Float result) {
            this.operand1 = operand1;
            this.operand2 = operand2;
            this.operation = operation;
            this.result = result;
        }

        Float getOperand1() {
            return operand1;
        }

        Float getOperand2() {
            return operand2;
        }

        char getOperation() {
            return operation;
        }

        Float getResult() {
            return result;
        }
    }
}
