/*В предыдущей задаче была рассмотрена система исполнения процессов «T-Saurus».
        В этой задаче вам предстоит реализовать core-функционал этой системы.
        Вместо минимального времени, за которое могут быть выполнены все процессы, вам необходимо перечислить их порядок, при котором достигается это время.
        Для этого вам необходимо разбить все процессы на непересекающиеся множества процессов (пронумеруем их от 1 до 𝑘) так,
        чтобы сначала исполнитель «T-Saurus» параллельно выполнил все процессы первого множества,
        затем второго множества и т.д., и исполнение процессов удовлетворяло условию из предыдущей задачи.
        Т. е. в i-ом множестве процессов должны присутствовать только те процессы,
        для которых все необходимые для их исполнения процессы включены в множествах с меньшими номерами j: 1≤𝑗<i.
        Напоминаем, что функционал системы «T-Saurus» состоит в том,
        что при последовательном исполнении предыдущих множеств процессов, все процессы в очередном множестве смогут исполниться.
        Входные данные совпадают с входными данными из предыдущей задачи.
        Обратите внимание на то, как должно выводиться каждое множество — все процессы в рамках множества должны быть отсортированы.
        Формат входных данных
        В первой строке дано число 𝑛 (1≤𝑛≤10^5)— количество процессов.
        Далее дано n строк. В i-й строке первым числом идёт 𝑎𝑖 — количество необходимых i-му процессу процессов.
        Далее идет ai чисел через пробел — их номера.
        Формат выходных данных
        В единственной строке выведите число k — количество множеств, на которое необходимо разбить все процессы.
        В следующих k строках выведите описание этих множеств:
        первым числом укажите размер множества ki, а далее через пробел ki чисел в порядке возрастания — номера процессов очередного множества.

Примеры данных
Пример 1
5
3 2 3 5
1 4
0
0
1 3

3
2 3 4
2 2 5
1 1
Пример 2
6
1 2
1 3
1 4
1 5
1 6
0

6
1 6
1 5
1 4
1 3
1 2
1 1
Пример 3
6
5 2 3 4 5 6
0
0
0
0
0

2
5 2 3 4 5 6
1 1
Пример 4
3
0
0
0

1
3 1 2 3

        */

import java.util.*;

public class T_SaurusSets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        List<List<Integer>> dependencies = new ArrayList<>(n + 1);
        int[] indegree = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dependencies.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            int ai = scanner.nextInt();
            for (int j = 0; j < ai; j++) {
                int dep = scanner.nextInt();
                dependencies.get(dep).add(i);
                indegree[i]++;
            }
        }

        List<List<Integer>> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            List<Integer> currentBatch = new ArrayList<>();

            while (!queue.isEmpty()) {
                currentBatch.add(queue.poll());
            }

            Collections.sort(currentBatch);
            result.add(currentBatch);

            for (int process : currentBatch) {
                for (int dependent : dependencies.get(process)) {
                    indegree[dependent]--;
                    if (indegree[dependent] == 0) {
                        queue.add(dependent);
                    }
                }
            }
        }

        System.out.println(result.size());
        for (List<Integer> batch : result) {
            System.out.print(batch.size() + " ");
            for (int process : batch) {
                System.out.print(process + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
