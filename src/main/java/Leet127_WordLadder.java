import java.util.*;

public class Leet127_WordLadder {
    public static int ladder(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if(!wordSet.contains(endWord))
            return 0;

        int lenS = beginWord.length();

        Set<String> exitSet = new HashSet<>();
        for(String s : wordSet) {
            if(tryWord(beginWord, s)) {
                exitSet.add(s);
            }
        }

        Deque<String> queue = new LinkedList<>();
        queue.addLast(endWord);

        int res = 0;

        while(!queue.isEmpty()) {
            //  值得学习的队列使用方法，不需要分层了
            for(int k = queue.size(); k > 0; --k) {
                String word = queue.pollFirst();

                if(exitSet.contains(word))
                    return res + 2;

                char[] newWord = word.toCharArray();
                for(int i = 0; i < lenS; ++i) {
                    char origin = newWord[i];

                    for(char c = 'a'; c <= 'z'; ++c) {
                        if(origin == c)
                            continue;

                        newWord[i] = c;
                        String sNewWord = String.valueOf(newWord);
                        if(!sNewWord.equals(word) && wordSet.contains(sNewWord)) {
                            queue.addLast(sNewWord);
                            wordSet.remove(sNewWord);
                        }
                    }
                    newWord[i] = origin;
                }
            }
            ++res;
        }

        return 0;
    }

    private static boolean tryWord(String word_1, String word_2) {
        boolean res = true;
        for(int i = 0; i < word_1.length(); ++i) {
            if(word_1.charAt(i) != word_2.charAt(i)) {
                res = !res;
                if(res)
                    return false;
            }
        }
        return !res;
    }

    public static void main(String[] args) {
        String begin = "hit";
        // String begin = "wog";
        String end = "cog";
        String[] s = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(ladder(begin, end, Arrays.asList(s)));
        // System.out.println(tryWord(begin, end));
    }
}
