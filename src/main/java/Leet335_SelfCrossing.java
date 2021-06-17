public class Leet335_SelfCrossing {
    public boolean isSelfCrossing(int[] distance) {
        for (int i = 3; i < distance.length; ++i) {
            if (distance[i] >= distance[i - 2] && distance[i - 3] >= distance[i - 1]) {
                return true;
            }
            if (i >= 4 && distance[i - 1] == distance[i - 3] && distance[i] >= distance[i - 2] - distance[i - 4]) {
                return true;
            }
            if (i >= 5 && distance[i - 2] >= distance[i - 4] && distance[i - 3] >= distance[i - 1]
                    && distance[i - 1] >= distance[i - 3] - distance[i - 5]
                    && distance[i] >= distance[i - 2] - distance[i - 4]) {
                return true;
            }
        }
        return false;
    }
}
