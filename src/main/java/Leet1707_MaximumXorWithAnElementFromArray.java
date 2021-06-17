import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

@SuppressWarnings("SuspiciousNameCombination")
public class Leet1707_MaximumXorWithAnElementFromArray {
    public int[] maximizeXor(int[] nums, int[][] queries) {
        Arrays.sort(nums);
        int numQ = queries.length;
        int[][] newQueries = new int[numQ][3];
        for (int i = 0; i < numQ; ++i) {
            newQueries[i][0] = queries[i][0];
            newQueries[i][1] = queries[i][1];
            newQueries[i][2] = i;
        }
        Arrays.sort(newQueries, Comparator.comparingInt(query -> query[1]));

        int[] ans = new int[numQ];
        Trie trie = new Trie();
        int idx = 0, n = nums.length;
        for (int[] query : newQueries) {
            int x = query[0], m = query[1], qid = query[2];
            while (idx < n && nums[idx] <= m) {
                trie.insert(nums[idx]);
                ++idx;
            }
            if (idx == 0) { // 字典树为空
                ans[qid] = -1;
            } else {
                ans[qid] = trie.getMaxXor(x);
            }
        }
        return ans;
    }

    static class Trie {
        static final int L = 30;
        Trie[] children = new Trie[2];

        public void insert(int val) {
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                int bit = (val >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new Trie();
                }
                node = node.children[bit];
            }
        }

        public int getMaxXor(int val) {
            int ans = 0;
            Trie node = this;
            for (int i = L - 1; i >= 0; --i) {
                int bit = (val >> i) & 1;
                if (node.children[bit ^ 1] != null) {
                    ans |= 1 << i;
                    bit ^= 1;
                }
                node = node.children[bit];
            }
            return ans;
        }
    }


    public int[] maximizeXor___(int[] nums, int[][] queries) {
        int[] ans = new int[queries.length];

        TreeNode dummyTop = new TreeNode(0);
        int top = 1;
        int min = Integer.MAX_VALUE;

        /*Build Trie*/
        for (int num : nums) {
            if (num < min)
                min = num;

            int tmp = num >> top;
            if (tmp > 0) {
                while (tmp > 0) {
                    TreeNode prevTop = dummyTop;
                    dummyTop = new TreeNode(prevTop.num);

                    if ((tmp & 0x01) == 1) {
                        dummyTop.iChild = prevTop;
                    } else {
                        dummyTop.xChild = prevTop;
                    }
                    tmp >>= 1;
                    ++top;
                }
            }

            int curLayer = top;
            int mask = 0x01 << top;
            TreeNode curNode = dummyTop;
            while (curLayer > 0) {
                mask >>= 1;
                ++curNode.num;
                if ((num & mask) == 0) {
                    if (curNode.xChild == null) {
                        curNode.xChild = new TreeNode(0);
                    }
                    curNode = curNode.xChild;
                } else {
                    if (curNode.iChild == null) {
                        curNode.iChild = new TreeNode(0);
                    }
                    curNode = curNode.iChild;
                }

                curLayer--;
            }

            curNode.value = num;
        }

        int index = 0;
        for (int[] query : queries) {
            int x = query[0];

            TreeNode curNode = dummyTop;
            int curLayer = top;
            int mask = 0x01 << top;

            // if mi < num_max
            if ((query[1] >> top) == 0) {
                //  Find Max in Trie below mi
                while (curLayer > 0) {
                    mask >>= 1;

                    if ((query[1] & mask) > 0) {
                        if (curNode.iChild == null) {
                            curNode = curNode.xChild;
                            break;
                        } else {
                            curNode = curNode.iChild;
                        }
                    } else {
                        if (curNode.xChild == null) {
                            curNode = null;
                            break;
                        } else {
                            curNode = curNode.xChild;
                        }
                    }

                    curLayer--;
                }
            }

            if (curNode == null) {
                ans[index++] = -1;
                continue;
            }

//            if (curNode.num == 0) {
//                ans[index++] = query[0] ^ curNode.value;
//                continue;
//            }
            int res = query[0];
            mask = 0x01 << curLayer;
            while (curLayer > 0) {
                mask >>= 1;
                if ((query[0] & mask) > 0) {
                    if (curNode.xChild == null) {
                        res ^= mask;
                        curNode = curNode.iChild;
                    } else {
                        curNode = curNode.xChild;
                    }
                } else {
                    if (curNode.iChild == null) {
                        curNode = curNode.xChild;
                    } else {
                        res ^= mask;
                        curNode = curNode.iChild;
                    }
                }
                curLayer--;
            }

            ans[index++] = res;
        }

        return ans;
    }

    private class TreeNode {
        TreeNode xChild;
        TreeNode iChild;

        int num;
        int value;

        TreeNode (int num) {
            this.num = num;
            this.value = -1;
        }
    }
}
