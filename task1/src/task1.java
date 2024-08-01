public class task1 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("The program must have 2 arguments");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        if (n <= 0) {
            System.out.println("The argument n must be greater than zero");
            return;
        }
        if (m <= 0) {
            System.out.println("The argument m must be greater than zero");
            return;
        }
        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }
        StringBuilder path = new StringBuilder();
        int index = 0;
        boolean pathIsDone = false;
        while (!pathIsDone) {
            path.append(circularArray[index]);
            index = (index + m - 1) % n;
            if (index == 0) pathIsDone = true;
        }
        System.out.println(path);
    }
}
