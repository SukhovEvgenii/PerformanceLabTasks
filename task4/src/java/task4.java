import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class task4 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("The program must have 1 argument");
            return;
        }
        String filePath = args[0];
        try {
            int[] nums;
            FileReader fr = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fr);
            nums = reader
                    .lines()
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (nums.length == 0) {
                System.out.println("Array is empty");
                return;
            }
            Arrays.sort(nums);
            int med = nums[nums.length / 2];
            int moves = 0;
            for (int num : nums) {
                moves += Math.abs(num - med);
            }
            System.out.println(moves);
        } catch (FileNotFoundException e) {
            System.out.println("Parsing error " + e);
        }
    }
}
