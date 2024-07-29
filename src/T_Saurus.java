/* Система исполнения «T-Saurus» разработана для параллельного выполнения процессов.
Инженеры самой высокой квалификации разработали её таким образом,
что одновременно может исполняться бесконечное количество процессов, т. е. вместимость исполнителя не ограничена.
Причем, после запуска процесса он успешно завершается ровно через одну секунду.
Несмотря на невероятную эффективность параллелизации,
некоторые процессы не могут быть начаты,
пока другие не завершат своё исполнение (так часто бывает в реальных системах,
когда один процесс использует результаты работы другого и соответственно не может быть запущен с ним параллельно).
При этом один процесс может ожидать завершения нескольких процессов.
Все процессы в системе пронумерованы от 1 до 𝑛.
Для каждого процесса известно, результаты работы каких процессов ему потребуются для исполнения.
Ваша задача состоит в том, чтобы определить, за какое минимальное количество секунд могут быть исполнены все процессы.
Гарантируется, что отсутствуют циклические зависимости, и процессы завершатся за конечное время.
Также гарантируется, что процесс с номером 1 всегда будет завершаться последним.

Формат входных данных
В первой строке дано число n (1 ≤ 𝑛 ≤ 10^5) — количество процессов. 
Далее дано 𝑛 строк. В i-й строке первым числом идёт 𝑎𝑖 — количество необходимых
i-му процессу процессов для старта. Далее идет 𝑎𝑖 чисел через пробел — их номера.
Формат выходных данных
В единственной строке выведите количество секунд — минимальное время, за которое все процессы смогут завершиться.

   Примеры данных
Пример 1
5
3 2 3 5
1 4
0
0
1 3

3
Пример 2
6
1 2
1 3
1 4
1 5
1 6
0

6
Пример 3
6
5 2 3 4 5 6
0
0
0
0
0

2
Пример 4
3
0
0
0

1
*/

import java.util.*;

public class T_Saurus {
    public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);

int n = scanner.nextInt();
List<List<Integer>> dependencies = new ArrayList<>(n + 1);
int[] inDegree = new int[n + 1];
int[] finishTime = new int[n + 1];

for (int i = 0; i <= n; i++) {
    dependencies.add(new ArrayList<>());
}

for (int i = 1; i <= n; i++) {
    int a = scanner.nextInt();
    inDegree[i] = a;
    for (int j = 0; j < a; j++) {
int dependency = scanner.nextInt();
dependencies.get(dependency).add(i);
    }
}

Queue<Integer> queue = new LinkedList<>();

for (int i = 1; i <= n; i++) {
    if (inDegree[i] == 0) {
queue.offer(i);
finishTime[i] = 1;
    }
}

while (!queue.isEmpty()) {
    int currentProcess = queue.poll();

    for (int dependent : dependencies.get(currentProcess)) {
inDegree[dependent]--;
finishTime[dependent] = Math.max(finishTime[dependent], finishTime[currentProcess] + 1);

if (inDegree[dependent] == 0) {
    queue.offer(dependent);
}
    }
}

System.out.println(finishTime[1]);
    }
}
