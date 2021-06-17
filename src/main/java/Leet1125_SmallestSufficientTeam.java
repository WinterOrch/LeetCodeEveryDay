import java.util.*;

public class Leet1125_SmallestSufficientTeam {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        HashMap<String, Integer> maskMap = new HashMap<>();
        for (int i = 0; i < req_skills.length; ++i) {
            maskMap.put(req_skills[i], 0x01 << i);
        }

        int numBuckets = 1 << req_skills.length;

        long[] dp = new long[numBuckets];
        Set<Integer> validStates = new HashSet<>();
        validStates.add(0);

        //  有很多人技能是完全重复的，用一个 HashSet 记录一下，重复的就不考虑了
        Set<Integer> skillSet = new HashSet<>();
        int numPerson = people.size();
        int[] candidates = new int[numPerson];
        for (int i = 0; i < numPerson; ++i) {
            List<String> skills = people.get(i);
            //  把啥都不会的先剪了
            if (skills.isEmpty())
                continue;

            for (String skill : skills) {
                candidates[i] |= maskMap.get(skill);
            }
            if (skillSet.contains(candidates[i]))
                continue;
            else
                skillSet.add(candidates[i]);

            Set<Integer> formerValidStates = new HashSet<>(validStates);

            for (int state : formerValidStates) {
                if ((state | candidates[i]) != state) {
                    int newState = state | candidates[i];

                    long member = dp[state];

                    if (!validStates.contains(newState)) {
                        dp[newState] = member | (1L << i);
                        validStates.add(newState);
                    } else if (Long.bitCount(member) + 1 < Long.bitCount(dp[newState])) {
                        dp[newState] = member | (1L << i);
                    }
                }
            }
        }

        long result = dp[numBuckets - 1];
        long mask = 1L;
        int[] ans = new int[Long.bitCount(result)];

        for (int i = 0, idx = 0; i < numPerson; ++i) {
            if ((result & mask) != 0) {
                ans[idx++] = i;
            }
            mask <<= 1;
        }

        return ans;
    }

//    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
//        HashMap<String, Integer> maskMap = new HashMap<>();
//        for (int i = 0; i < req_skills.length; ++i) {
//            maskMap.put(req_skills[i], 0x01 << i);
//        }
//
//        Map<Integer, int[]> dp = new HashMap<>();
//        dp.put(0, new int[0]);
//
//        int numPerson = people.size();
//        int[] candidates = new int[numPerson];
//        for (int i = 0; i < numPerson; ++i) {
//            for (String skill : people.get(i)) {
//                candidates[i] |= maskMap.get(skill);
//            }
//
//            Set<Map.Entry<Integer, int[]>> originEntrySet = new HashSet<>(dp.entrySet());
//
//            for (Map.Entry<Integer, int[]> entry :originEntrySet) {
//                int key = entry.getKey();
//                if ((key | candidates[i]) != key) {
//                    int newKey = key | candidates[i];
//
//                    int[] oriMember = entry.getValue();
//                    if (dp.containsKey(newKey)) {
//                        if (oriMember.length + 1 < dp.get(newKey).length) {
//                            int[] members = new int[oriMember.length + 1];
//                            System.arraycopy(oriMember, 0, members, 0, oriMember.length);
//                            members[oriMember.length] = i;
//                            dp.put(newKey, members);
//                        }
//                    } else {
//                        int[] members = new int[oriMember.length + 1];
//                        System.arraycopy(oriMember, 0, members, 0, oriMember.length);
//                        members[oriMember.length] = i;
//                        dp.put(newKey, members);
//                    }
//                }
//            }
//        }
//
//        return dp.get((1 << req_skills.length) - 1);
//    }

    public static void main(String[] args) {
        Leet1125_SmallestSufficientTeam ins = new Leet1125_SmallestSufficientTeam();

        String[] req_skills = {"algorithms","math","java","reactjs","csharp","aws"};
        String[][] people = {{"algorithms","math","java"},{"algorithms","math","reactjs"},{"java","csharp","aws"},
                {"reactjs","csharp"},{"csharp","math"},{"aws","java"}};

        List<List<String>> peopleInList = new ArrayList<>();
        for (String[] person : people) {
            peopleInList.add(Arrays.asList(person));
        }

        System.out.println(Arrays.toString(ins.smallestSufficientTeam(req_skills, peopleInList)));
    }
}
