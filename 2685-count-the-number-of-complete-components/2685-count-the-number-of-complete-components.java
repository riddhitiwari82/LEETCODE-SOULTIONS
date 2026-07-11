 class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    int vertices;
    int degreeSum;

    public int countCompleteComponents(int n, int[][] edges) {

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        visited = new boolean[n];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                vertices = 0;
                degreeSum = 0;

                dfs(i);

                if (degreeSum == vertices * (vertices - 1)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private void dfs(int node) {
        visited[node] = true;
        vertices++;
        degreeSum += graph[node].size();

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}  
    