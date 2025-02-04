import java.util.Random;

public class Matrix_Search_sort {
    static final int SIZE = 1000;
    static int[][] matrix = new int[SIZE][SIZE];
    static Random rand = new Random();

    public static void ganerate_Matrix(){
    for(int i = 0; i < SIZE; i++){
        for(int j = 0; j < SIZE; j++){
            matrix[i][j] = rand.nextInt(2001) - 1000;
        }
    }
    }

    public static boolean sequential_Search(int x){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if (matrix[i][j] == x) {
                    return true;
                }
            }
        }
        return false;

    }

    public static boolean binary_Search(int[] arr, int x){
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] == x) return true;
            if(arr[mid] < x) left = mid + 1;
            else right = mid - 1;

        }
        return false;
    }

    public static boolean interpolation_Search(int[] arr, int x){
        int low = 0, high = arr.length - 1;
        while (low <= high && x >= arr[low] && x <= arr[high]) {
            int pos = low + (((x - arr[low]) * (high - low)) / (arr[high] - arr[low]));
            if(arr[pos]== x) return true;
            if(arr[pos] < x) low = pos + 1;
            else high = pos - 1;
        }
        return false;
    }

    public static void bubble_Sort(int[] arr){
        int n =  arr.length;
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i -1; j++){
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void insertion_Sort(){

    }

    public static void main(String[] args){
        ganerate_Matrix();
    }
}
