// Time Complexity: O(m * n)
// Space Complexity: O(m * n)

import java.util.HashMap;

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        HashMap<Integer, List<int[]>> map = new HashMap<>();
        int m = workers.length;
        int n = bikes.length;

        int min = 2000;
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int[] b = bikes[i];
                int[] w = workers[j];
                int dist = calcDist(b, w);
                if (!map.containsKey(dist))
                    map.put(dist, new ArrayList<>());
                map.get(dist).add(new int[] { i, j });

                min = Math.min(min, dist);
                max = Math.max(max, dist);
            }
        }

        boolean[] occupied = new boolean[n];
        boolean[] assigned = new boolean[m];
        int[] result = new int[m];
        int count = 0;

        for (int dist = min; dist <= max; dist++) {
            List<int[]> li = map.get(dist);
            if (li != null) {
                for (int[] pair : li) {
                    int w = pair[1];
                    int b = pair[0];
                    if (!occupied[b] && !assigned[w]) {
                        occupied[b] = true;
                        assigned[w] = true;
                        result[w] = b;
                    }

                    if (count == m)
                        return result;

                }
            }
        }

        return result;
    }

    private int calcDist(int[] b, int[] w) {
        return Math.abs(b[0] - w[0]) + Math.abs(b[1] - w[1]);
    }
}