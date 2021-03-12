package practice.java.sort;

import com.sun.webkit.dom.DocumentFragmentImpl;

import java.util.Arrays;

class MyThread {
    private int number = 1;

    public void print1() throws InterruptedException {
        synchronized(this) {
            while (number != 1) {
                this.wait();
            }
            number = 2;
            System.out.println(1);
            this.notifyAll();
        }
    }

    public void print10() throws InterruptedException {
        synchronized(this) {
            while (number != 2) {
                this.wait();
            }
            number = 1;
            System.out.println(10);
            this.notifyAll();
        }
    }
}

public class BubbleSort {

    public static void main(String[] args) {
        /*MyThread myThread = new MyThread();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    myThread.print1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    myThread.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
//        int[] list = {10, 2, 3, 7, 8, 6, 5, 1, 9};
//        //bubbleSort(list);
//        quickSort(list, 0, list.length - 1);
//        System.out.println(Arrays.toString(list));
//        System.out.println(binarySearch(list, 7));
//
//        System.out.println(findMissing(list));
        System.out.println(fi(4));
    }

    public static void bubbleSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            for(int j = list.length - 1; j > i; j--) {
                if (list[j - 1] > list[j]) {
                    int temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                }
            }
        }
    }

    public static void quickSort(int[] list, int left, int right) {
        if (left >= right) {
            return;
        }

        int i = left;
        int j = right;
        int base = list[left];

        while (i != j) {
            while (i < j && list[j] >= base) {
                j--;
            }

            while (i < j && list[i] <= base) {
                i++;
            }

            if (i < j) {
                int tmp = list[j];
                list[j] = list[i];
                list[i] = tmp;
            }
        }

        list[left] = list[i];
        list[i] = base;

        quickSort(list, left, i - 1);
        quickSort(list, i + 1, right);
    }

    public static int binarySearch(int[] list, int key) {
        int left = 0;
        int right = list.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (key < list[mid]) {
                right = mid - 1;
            } else if (key > list[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -(1 + left);
    }

    public static int findMissing(int[] nums) {
        int lenNums = nums.length;
        if (lenNums <= 0) {
            return 0;
        }
        long sumNums = 0, sumN = 0;
        for (int i = 0; i < lenNums; i++) {
            sumNums += nums[i];
            sumN += (i + 1);
        }
        sumN = sumN + lenNums + 1;
        return (int) (sumN - sumNums);
    }

    public static int fi(int n) {
        if(n < 1){
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return fi(n - 1) + fi(n - 2);
    }
}
