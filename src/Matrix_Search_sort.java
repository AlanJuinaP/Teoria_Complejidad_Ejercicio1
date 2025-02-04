import java.util.Arrays;
import java.util.Random;

public class Matrix_Search_sort {
    static final int SIZE = 1000;//tamanio de matriz
    static int[][] matrix = new int[SIZE][SIZE];
    static Random rand = new Random();

    //generara matriz con valores aleatorios entre -1000 y 1000
    public static void ganerate_Matrix(){
    for(int i = 0; i < SIZE; i++){
        for(int j = 0; j < SIZE; j++){
            matrix[i][j] = rand.nextInt(2001) - 1000;
        }
    }
    }

    //busqueda secuencial
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

    //busqueda binaria
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

    //busqueda por interpolacion 
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

    // metodo ordenacion burbuja
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

    //metodo ordenacion por insercion
    public static void insertion_Sort(int[] arr){
        int n = arr.length;
        for(int i = 1; i < n; i++){
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
           }
           arr[j + 1] = key;
        }
    }

    //metodo de ordenacion merge
    public static void mergeSort(int[] arr, int left, int right){
        if (left < right) {
            int mid = left + (right - left)/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            mergeSort(arr, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right){
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] L =  new int[n1];
        int[] R =  new int[n2];
        for(int i = 0; i < n1; i++) L[i] = arr[left + i];
        for(int i = 0; i < n2; i++) R[i] = arr[mid + 1 + i];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if(L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    //metodo por ordenacion shell
    public static void shell_Sort(int[] arr){
        int n = arr.length;
        for(int gap = n/2; gap > 0; gap /= 2){
            for(int i = gap; i < n; i++){
                int temp = arr[i];
                int j;
                for(j = i; j >= gap && arr[j - gap] > temp; j -= gap){
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }

        }
    }

     // Counting Sort: ordenación basada en conteo de frecuencias
     public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        int min = Arrays.stream(arr).min().orElse(0);
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];
        
        for (int num : arr) count[num - min]++;
        for (int i = 1; i < count.length; i++) count[i] += count[i - 1];
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }
    
    // Radix Sort: ordenación basada en los dígitos de los números
    public static void radixSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortForRadix(arr, exp);
        }
    }
    
    // Subrutina de Counting Sort utilizada en Radix Sort para cada dígito
    private static void countingSortForRadix(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];
        
        for (int num : arr) count[(num / exp) % 10]++;
        for (int i = 1; i < 10; i++) count[i] += count[i - 1];
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args){
        ganerate_Matrix();

        int[] array = new int[SIZE * SIZE];
        int index = 0;
        for(int i = 0; i < SIZE; i++){
            for(int j=0; j < SIZE; j++){
                array[index++] = matrix[i][j];
            }
        }

        Arrays.sort(array);

        //Ordenamiento del array para realizar la busqueda solicitada
        int x = rand.nextInt(2001) - 1000;
        boolean foundseq = sequential_Search(x);
        boolean foundBin = binary_Search(array, x);
        boolean foundInterpol = interpolation_Search(array, x);

        System.out.println("Numero " + x + "encontrado (Busqueda Secuencial): " + foundseq);
        System.out.println("Numero " + x + "encontrado (Busqueda Binaria): " + foundBin);
        System.out.println("Numero " + x + "encontrado (Busqueda Interpolacion): " + foundInterpol);

        //Aplicar algoritmos de ordenacion
        bubble_Sort(array);
        System.out.println("Bubble Sort completado.");
        
        insertion_Sort(array);
        System.out.println("Insertion Sort completado.");
        
        mergeSort(array, 0, array.length - 1);
        System.out.println("Merge Sort completado.");
        
        shell_Sort(array);
        System.out.println("Shell Sort completado.");

        countingSort(array);
        System.out.println("Counting Sort completado.");
        
        radixSort(array);
        System.out.println("Radix Sort completado.");
    }
}
