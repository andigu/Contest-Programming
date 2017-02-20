package contests.tle16c6s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class s3 {
    public static int solve(int x, int energy, Map<Integer, Integer> houses) {
        int ans =0;
        boolean entered = false;
        for (Integer loc : houses.keySet()) {
            if (loc != x && energy - Math.abs(loc - x) >=0 && houses.get(loc) != 0) {
                entered = true;
                int temp = houses.get(loc);
                houses.put(loc, 0);
                ans = Math.max(ans, solve(loc, energy + temp - Math.abs(loc - x), houses) + Math.abs(loc - x));
                houses.put(loc, temp);
            }
        }
        if (!entered) {
            return energy;
        }
        else {
            return ans;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> houses = new HashMap<>();
        int n = Integer.parseInt(reader.readLine().trim());
        for (int i=0; i < n; i++) {
            String[] data = reader.readLine().trim().split(" ");
            houses.put(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        }
        int temp = houses.get(0);
        houses.put(0, 0);
        System.out.println(solve(0, temp, houses));
    }
}
