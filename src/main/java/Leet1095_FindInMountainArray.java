public class Leet1095_FindInMountainArray {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int n = mountainArr.length(), left = 0, right = n - 1, peak = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        peak = left;
        if (mountainArr.get(peak) == target) {
            return peak;
        }
        int idx1 = helper(target, mountainArr, 0, peak - 1, true);
        int idx2 = helper(target, mountainArr, peak + 1, n - 1, false);
        return idx1 == -1 ? idx2 : idx1;
    }

    int helper(int target, MountainArray mountainArray, int left, int right, boolean isAsc) {
        while (left < right) {
            int mid = left + (right - left) / 2, cur = mountainArray.get(mid);
            if (cur == target)
                return mid;
            else if (cur < target) {
                if (isAsc)
                    left = mid + 1;
                else
                    right = mid;
            } else {
                if (isAsc)
                    right = mid;
                else
                    left = mid + 1;
            }
        }

        return mountainArray.get(right) == target ? right : -1;
    }

    interface MountainArray {
        int get(int index);
        int length();
    }
}
