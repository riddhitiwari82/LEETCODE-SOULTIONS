class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        for (int num : nums) {
            for (int cnt = 3; cnt >= 1; cnt--) {
                for (int x = 0; x < MAX; x++) {
                    if (dp[cnt - 1][x]) {
                        dp[cnt][x ^ num] = true;
                    }
                }
            }
        }

        boolean[] seen = new boolean[MAX];

        // Triplets with repeated indices contribute only original values.
        for (int num : nums) {
            seen[num] = true;
        }

        // XORs from three distinct indices.
        for (int x = 0; x < MAX; x++) {
            if (dp[3][x]) {
                seen[x] = true;
            }
        }

        int ans = 0;
        for (boolean b : seen) {
            if (b) ans++;
        }
        return ans;
    }
}