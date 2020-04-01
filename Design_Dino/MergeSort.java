public class MergeSort
{
    public static void main(String[] args)
    {
        int N = 100000;
        int[] a = getRandom(N, 100000, -100000);

        for(int i = 0; i < N; i++)
        {
            System.out.print(a[i] + " ");
        }


        System.out.println();
        //merge(a, 0, 3, 3, 6);
        mergeSort(a, 0, N);

        for(int i = 0; i < N; i++)
        {
            System.out.print(a[i] + " ");
        }
    }

    private static int[] getRandom(int N, int hi, int lo)
    {
        int[] a = new int[N];

        for(int i = 0; i < N; i++)
        {
            a[i] = lo + (int)(Math.random() * (hi - lo));
        }

        return a;
    }

    private static void mergeSort(int[] a, int i, int j)
    {
        if(j - i <= 1) return;
        else
        {
            int mid = (i+j)/2;

            mergeSort(a, i, mid);
            mergeSort(a, mid, j);

            merge(a, i, mid, mid, j);
        }
    }

    public static void merge(int[] a, int i1, int j1, int i2, int j2)
    {
        int[] tmp = new int[j2-i1];

        int k = 0;

        int p1 = i1;
        int p2 = i2;

        while (p1 < j1 && p2 < j2)
        {
            if(a[p1] < a[p2])tmp[k++] = a[p1++];
            else tmp[k++] = a[p2++];
        }

        while(p1 < j1)
            tmp[k++] = a[p1++];
        while(p2 < j2)
            tmp[k++] = a[p2++];

        System.arraycopy(tmp, 0, a, i1, j2-i1);

        return;
    }
}
