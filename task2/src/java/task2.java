import java.io.*;

public class task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("The program must have 2 arguments");
            return;
        }
        File fileWithCircleParameters = new File(args[0]);
        File fileWithDotsCoordinates = new File(args[1]);
        byte maxDotsCount = 100;
        try {
            FileReader fr = new FileReader(fileWithCircleParameters);
            BufferedReader reader = new BufferedReader(fr);
            fr.close();
            String[] circleCenter = reader.readLine().split(" ");
            int circleRadius = Integer.parseInt(reader.readLine());
            FileReader fileReader = new FileReader(fileWithDotsCoordinates);
            reader = new BufferedReader(fileReader);
            fileReader.close();
            String dotCoordinates = reader.readLine();
            byte dotsCount = 0;
            while(dotCoordinates != null) {
                if (dotsCount >= maxDotsCount) return;
                String[] coordinates = dotCoordinates.split(" ");
                double hypotenuse = Math.sqrt(Math.pow(Integer.parseInt(coordinates[0]) - Integer.parseInt(circleCenter[0]), 2)
                                            + Math.pow(Integer.parseInt(coordinates[1]) - Integer.parseInt(circleCenter[1]), 2));
                if (hypotenuse > circleRadius) System.out.println("2");
                if (hypotenuse == circleRadius) System.out.println("0");
                if (hypotenuse < circleRadius) System.out.println("1");
                dotsCount++;
                dotCoordinates = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
