package com.javarush.task.task34.task3401;

/* 
Числа Фибоначчи с помощью рекурсии
Почитай про числа Фибоначчи.
Чи́сла Фибона́ччи — элементы последовательности
0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, … , в которой первые два числа равны 
либо 1 и 1, либо 0 и 1, а каждое последующее число равно сумме двух предыдущих чисел

Реализуй рекурсивную логику метода fibonacci, где n — это номер элемента в последовательности Фибоначчи.
Не создавай в классе Solution дополнительные поля.

Требования:
1. В классе Solution не должны быть созданы дополнительные поля.
2. Метод fibonacci должен принимать порядковый номер искомого числа последовательности Фибоначчи и возвращать его значение.
3. Метод fibonacci не должен быть статическим.
4. Метод fibonacci должен быть рекурсивным*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.fibonacci(9));     //34
        System.out.println(solution.fibonacci(5));     //5
        System.out.println(solution.fibonacci(2));     //1
        System.out.println(solution.fibonacci(1));     //1
    }

    public int fibonacci(int n) {
        if(n==0)
            return 0;
        if(n==1 || n==2)
            return 1;
        int res = fibonacci(n-1) + fibonacci(n-2);

        return res;
    }
}
