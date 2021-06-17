package mods;

import java.util.*;

/**
 * 扫描线问题
 * 这里是一道提供飞机起降时间，求空中瞬时最大架数
 * created in 16:27 2021/4/11
 */
public class SweepLine {
    class Interval {
        int start, end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int countOfAirplanes(List<Interval> airplanes) {
        List<Point> list = new ArrayList<>(airplanes.size() * 2);

        for(Interval i : airplanes) {
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }

        list.sort(Point.PointComparator);

        int count = 0, ans = 0;
        for(Point p : list) {
            if(p.flag == 1)
                ++count;
            else
                --count;
            ans = Math.max(ans, count);
        }

        return ans;
    }
}

class Point{
    private int time;
    int flag;

    Point(int t, int f) {
        this.time = t;
        this.flag = f;
    }

    public static Comparator<Point> PointComparator = Comparator.comparingInt((Point p) -> p.time)
            .thenComparingInt((Point p) -> p.flag);
}