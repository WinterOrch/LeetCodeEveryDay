public class Leet55_JumpGame {
    public boolean canJump(int[] nums) {
        int curIdx = 0;

        while(true) {
            if(curIdx + nums[curIdx] >= nums.length - 1)
                return true;

            int nextStep = 0;
            int dis = nums[curIdx];

            for(int i = 1; i < nums[curIdx]; ++i) {
                if(nums[curIdx + i] + i > dis) {
                    nextStep = i;
                    dis = nums[curIdx + i] + i;
                }
            }

            if(0 == dis)
                return false;
            else if(0 == nextStep)
                curIdx += dis;
            else
                curIdx += nextStep;
        }
    }

    public static void main(String[] args) {
        Leet55_JumpGame ins = new Leet55_JumpGame();
        int[] nums = {3,2,1,0,4};

        System.out.println(ins.canJump(nums));
    }
}
