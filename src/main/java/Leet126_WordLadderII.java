import java.util.*;

public class Leet126_WordLadderII {
    /**
     * 双向BFS效果更佳
     * created in 11:20 2021/3/24
     */
    private List<List<String>> findLadderRemastered(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        if(wordSet.isEmpty() || !wordSet.contains(endWord))
            return res;

        // 双向BFS得到后续结点列表 sucMap
        Map<String, Set<String>> sucMap = new HashMap<>();
        if(!bidirectionalBFS(beginWord, endWord, wordSet, sucMap))
            return res;

        // 基于 sucMap 通过DFS回溯得到最短路径列表
        Deque<String> path = new ArrayDeque<>();
        path.addLast(beginWord);
        dfsRemastered(beginWord, endWord, sucMap, path, res);
        return res;
    }

    private boolean bidirectionalBFS(String beginWord, String endWord, Set<String> wordSet,
                                     Map<String, Set<String>> suc) {
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        visited.add(endWord);

        Set<String> frontVis = new HashSet<>();
        frontVis.add(beginWord);
        Set<String> backVis = new HashSet<>();
        backVis.add(endWord);

        int wordLen = beginWord.length();
        boolean frontDir = true;
        boolean found = false;

        while(!frontVis.isEmpty() && !backVis.isEmpty()) {
            // 选择较小的Set进行处理，扩散更小，统计意义上搜索效率更高
            if(frontVis.size() > backVis.size()) {
                Set<String> tmpSet = frontVis;
                frontVis = backVis;
                backVis = tmpSet;

                frontDir = !frontDir;
            }
            Set<String> nextVis = new HashSet<>();
            for(String curWord : frontVis) {
                char[] curArray = curWord.toCharArray();
                for(int i = 0; i < wordLen; ++i) {
                    char originChar = curArray[i];
                    for(char j = 'a'; j <= 'z'; ++j) {
                        if(originChar == j)
                            continue;
                        curArray[i] = j;
                        String nextWord = new String(curArray);

                        if(wordSet.contains(nextWord)) {
                            if(backVis.contains(nextWord)) {
                                found = true;
                                addToSuccessors(suc, frontDir, curWord, nextWord);
                            }

                            if(!visited.contains(nextWord)) {
                                nextVis.add(nextWord);
                                addToSuccessors(suc, frontDir, curWord, nextWord);
                            }
                        }
                    }
                    curArray[i] = originChar;
                }
            }
            frontVis = nextVis;
            visited.addAll(nextVis);
            if(found)
                break;
        }
        return found;
    }

    private void addToSuccessors(Map<String, Set<String>> successors, boolean forward,
                                 String curWord, String nexWord) {
        if(!forward) {
            String tmp = curWord;
            curWord = nexWord;
            nexWord = tmp;
        }

        successors.computeIfAbsent(curWord, a -> new HashSet<>());
        successors.get(curWord).add(nexWord);
    }

    private void dfsRemastered(String beginWord, String endWord,
                               Map<String, Set<String>> suc, Deque<String> path, List<List<String>> res) {
        if(beginWord.equals(endWord)) {
            res.add(new ArrayList<>(path));
            return;
        }

        if(!suc.containsKey(beginWord)) {
            return;
        }

        Set<String> sucSet = suc.get(beginWord);
        for(String successor : sucSet) {
            path.addLast(successor);
            dfsRemastered(successor, endWord, suc, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        String begin = "hit";
        // String begin = "wog";
        String end = "cog";
        String[] s = {"hot", "dot", "dog", "lot", "log", "cog"};

        // System.out.println(tryWord(begin, end));
        Leet126_WordLadderII solution = new Leet126_WordLadderII();
        List<List<String>> res = solution.findLadderRemastered(begin, end, Arrays.asList(s));

        for(List<String> ls : res) {
            for(String cs : ls)
                System.out.print(cs + " ");
            System.out.println();
        }
    }
}
