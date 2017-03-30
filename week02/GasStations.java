package week02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GasStations {
    public static List<Integer> getGasStations(int tripDistance, int tankSize, List<Integer> gasStations) {
        List<Integer> stations = new ArrayList<>();
        int fuel = tankSize - gasStations.get(0);

        int i = 0;
        while (gasStations.get(i) + fuel < tripDistance) {
            if (fuel >= gasStations.get(i + 1)) {
                fuel -= gasStations.get(i + 1) - gasStations.get(i);
            } else {
                stations.add(gasStations.get(i));
                fuel = tankSize;
            }
            i++;
        }

        return stations;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int tripDistance = scanner.nextInt();
        int tankSize = scanner.nextInt();

        int gasStationsCount = scanner.nextInt();
        List<Integer> gasStations = new ArrayList<>();

        for (int i = 0; i < gasStationsCount; i++) {
            gasStations.add(scanner.nextInt());
        }

        List<Integer> result = getGasStations(tripDistance, tankSize, gasStations);

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }
}
