
        class Solution {
    static final int MOD = 1_000_000_007;

    class Node {
        long value;
        long sum;
        int cnt;

        Node() {}

        Node(long value, long sum, int cnt) {
            this.value = value;
            this.sum = sum;
            this.cnt = cnt;
        }
    }

    Node[] tree;
    long[] pow10;
    String s;

    public int[] sumAndMultiply(String s, int[][] queries) {
        this.s = s;
        int n = s.length();

        // Precompute powers of 10 modulo MOD
        pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        tree = new Node[4 * n];
        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            Node res = query(1, 0, n - 1, queries[i][0], queries[i][1]);
            ans[i] = (int) ((res.value * (res.sum % MOD)) % MOD);
        }

        return ans;
    }

    private void build(int idx, int l, int r) {
        if (l == r) {
            int digit = s.charAt(l) - '0';

            if (digit == 0) {
                tree[idx] = new Node(0, 0, 0);
            } else {
                tree[idx] = new Node(digit, digit, 1);
            }
            return;
        }

        int mid = (l + r) / 2;

        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    private Node query(int idx, int l, int r, int ql, int qr) {
        if (r < ql || l > qr)
            return new Node(0, 0, 0);

        if (ql <= l && r <= qr)
            return tree[idx];

        int mid = (l + r) / 2;

        Node left = query(idx * 2, l, mid, ql, qr);
        Node right = query(idx * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }

    private Node merge(Node left, Node right) {
        Node res = new Node();

        res.cnt = left.cnt + right.cnt;
        res.sum = left.sum + right.sum;

        res.value = (left.value * pow10[right.cnt]) % MOD;
        res.value = (res.value + right.value) % MOD;

        return res;
    }
}
        
   