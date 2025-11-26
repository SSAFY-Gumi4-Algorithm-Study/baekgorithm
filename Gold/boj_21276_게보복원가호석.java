import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class boj_21276_게보복원가호석 {
    // 자식 저장 트리맵
    static Map<String, TreeSet<String>> childrenMap = new TreeMap<>();

    // 자식 + 자손 저장 해시맵
    static Map<String, List<String>> descendantMap = new HashMap<>();

    // 진입차수 해시맵
    static Map<String, Integer> indegree = new HashMap<>();

    // 가문의 시조들 저장 하는 트리셋
    static Set<String> root = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        // 자식 저장 트리맵
        childrenMap = new TreeMap<>();

        // 자식 + 자손 저장 해시맵
        descendantMap = new HashMap<>();

        // 진입차수 해시맵
        indegree = new HashMap<>();

        // 가문의 시조들 저장 하는 트리셋
        root = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();

            childrenMap.put(name, new TreeSet<>());
            descendantMap.put(name, new ArrayList<>());
            indegree.put(name, 0);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            String descendant = st.nextToken();
            String ancestor = st.nextToken();

            descendantMap.get(ancestor).add(descendant);

            indegree.put(descendant, indegree.get(descendant) + 1);
        }

        topologicalSort();

        StringBuilder sb = new StringBuilder();
        sb.append(root.size()).append("\n");

        for (String name : root) {
            sb.append(name).append(" ");
        }
        sb.append("\n");

        for (Map.Entry<String, TreeSet<String>> entry : childrenMap.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue().size()).append(" ");

            for (String child : entry.getValue()) {
                sb.append(child).append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void topologicalSort() {
        Queue<String> q = new ArrayDeque<>();

        for (Map.Entry<String, Integer> entry : indegree.entrySet()) {
            if (entry.getValue() == 0) {
                root.add(entry.getKey());
                q.offer(entry.getKey());
            }
        }

        while (!q.isEmpty()) {
            String name = q.poll();

            for (String descendant : descendantMap.get(name)) {
                indegree.put(descendant, indegree.get(descendant) - 1);
                if (indegree.get(descendant) == 0) {// indegree가 0이될 때 조상이 자신의 부모
                    q.add(descendant);
                    childrenMap.get(name).add(descendant);
                }
            }
        }
    }
}
