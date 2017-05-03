package algodat;

import java.util.Arrays;

public class Merge {
    public static void mergeSort(int[] i) {
        int[] l = Arrays.copyOfRange(i, 0, i.length / 2);
        int[] r = Arrays.copyOfRange(i, i.length / 2, i.length);
        if (r.length > 1) {
            mergeSort(l);
            mergeSort(r);
        }
        merge(i, l, r);
    }

    public static void merge(int[] original, int[] l, int[] r) {
        int indexo = 0;
        int indexl = 0;
        int indexr = 0;
        while (indexl < l.length && indexr < r.length) {
            if (l[indexl] < r[indexr]) {
                original[indexo] = l[indexl];
                indexl++;
            } else {
                original[indexo] = r[indexr];
                indexr++;
            }
            indexo++;
        }
        while (indexl < l.length) {
            original[indexo] = l[indexl];
            indexl++;
            indexo++;
        }

        while (indexr < r.length) {
            original[indexo] = r[indexr];
            indexr++;
            indexo++;
        }
    }
}
