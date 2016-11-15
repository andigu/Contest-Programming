package contests.ccc._2014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Andi Gu
 */
public class s4 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine().split(" ")[0]);
        int t = Integer.parseInt(reader.readLine().split(" ")[0]);
        long[][] rectangles = new long[n][5];
        for (int i=0; i < n; i++) {
            rectangles[i] = Arrays.stream(reader.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }
        Set<Long> iToX = new HashSet<>(), iToY = new HashSet<>();
        for (long[] row : rectangles) {
            iToX.add(row[0]);
            iToX.add(row[2]);
            iToY.add(row[1]);
            iToY.add(row[3]);
        }
        List<Long> iToXArray = new ArrayList<>(iToX), iToYArray = new ArrayList<>(iToY);
        Collections.sort(iToXArray);
        Collections.sort(iToYArray);

        Map<Long, Integer> xToI = new HashMap<>(), yToI = new HashMap<>();
        for (int i=0; i < iToXArray.size(); i ++) {
            xToI.put(iToXArray.get(i), i);
        }
        for (int i=0; i < iToYArray.size(); i ++) {
            yToI.put(iToYArray.get(i), i);
        }


        long[][] differenceArray = new long[iToY.size()][iToX.size()];
        for (int i=0; i < n; i ++) {
            for (int j=yToI.get(rectangles[i][1]); j < yToI.get(rectangles[i][3]); j ++) {
                differenceArray[j][xToI.get(rectangles[i][0])] += rectangles[i][4];
                differenceArray[j][xToI.get(rectangles[i][2])] -= rectangles[i][4];
            }
        }
        long ans = 0;
        for (int i=0; i < iToYArray.size() - 1; i++) {
            for (int j=0; j < iToXArray.size()- 1; j ++) {
                differenceArray[i][j + 1] += differenceArray[i][j];
                if (differenceArray[i][j] >= t) {
                    ans += (iToYArray.get(i + 1) - iToYArray.get(i)) * (iToXArray.get(j+1) - iToXArray.get(j));
                }
            }
        }
        System.out.println(ans);
    }
}
