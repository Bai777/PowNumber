package org.example.problem;


import java.util.Objects;

public class Problem {
    static int[][] things = {{5, 2}, {2, 7}, {1, 3}, {7, 7}, {6, 8}, {3, 9}};
    static int[][] oldThings;
    static int[][] unusedThings;
    static int maxWeight = 10;
    static int count = things.length;


    public static void main(String[] args) {

        sortByPosition();
        oldThings = things;
        int[][] bestSet = getBest(things, null);
        print(bestSet);
        int unusedCount = 0;
        for (int i = 0; i < bestSet.length; i++) {
            if (bestSet[i][0] == 0) {
                unusedCount += 1;
            }
        }
        System.out.println(unusedCount);
        unusedThings = new int[unusedCount][2];
        for (int i = 0; i < unusedThings.length; i++) {
            unusedThings[i][0] = 0;
        }
        for (int j = 0; j < bestSet.length; j++) {
            if (bestSet[j][0] != 0) {
                for (int z = 0; z < things.length; z++) {
                    if (Objects.equals(things[z], bestSet[j])) {
                        things[z] = null;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < unusedThings.length; i++) {
            for (int z = 0; z < things.length; z++) {
                if (things[z] != null) {
                    unusedThings[i] = things[z];
                    things[z] = null;
                    break;
                }
            }
        }
        print(unusedThings);

    }

    static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println("Weight: " + a[i][0] + "; Price: " + a[i][1]);
        }
    }

    static int[][] getBest(int[][] thingsArr, int[][] oldBest) {
        int[][] best;
        if (oldBest == null) {
            best = new int[count][2];
            for (int i = 0; i < best.length; i++) {
                best[i] = new int[2];
                best[i][0] = 0;
            }
        } else best = oldBest;

        int weig = 0;
        for (int i = 0; i < best.length; i++) {
            if (best[i] != null) {
                weig += best[i][0];
            }
        }
        int[][] workArr = new int[count][2];
        for (int i = 0; i < thingsArr.length; i++) {
            workArr[i] = thingsArr[i];
        }

        int minW;

        for (int i = 0; i < best.length; i++) {
            if (best[i][0] == 0) {
                for (int j = 0; j < workArr.length; j++) {
                    if (workArr[j] != null && workArr[j][0] < maxWeight - weig) {
                        best[i] = workArr[j];
                        weig += workArr[j][0];
                        workArr[j] = null;
                        break;
                    }
                }
                break;
            }
        }
        minW = minWeight(workArr);
        if (minW != 0 && maxWeight - weig > minW) {
            best = getBest(workArr, best);
        }
        return best;
    }

    static int minWeight(int[][] arr) {
        int minW = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                minW = arr[i][0];
                break;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i][0] < minW) {
                minW = arr[i][0];
            }
        }
        return minW;
    }
    static void sortByPosition() {
        for (int j = 0; j < things.length; j++) {
            for (int i = 0; i < things.length - 1; i++) {
                if (getPos(things[i]) < getPos(things[i + 1])) {
                    int[] u = {things[i][0], things[i][1]};
                    things[i] = things[i + 1];
                    things[i + 1] = u;
                }
            }
        }
    }
    static float getPos(int[] t) {
        return t[1] / t[0];
    }
}

