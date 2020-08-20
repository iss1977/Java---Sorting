import java.util.*;


public class BoubleSort {

    public static void sortArray(ArrayList<Integer> alist){
        int lastIndex = alist.size()-1;

        for (int i=lastIndex; i>=0 ; i--){
            for (int j=0; j<=i-1; j++){
                if (alist.get(j) > alist.get(j+1) ){
                    int temp = alist.get(j) ;
                    alist.set(j,alist.get(j+1));
                    alist.set(j+1,temp);
                }

            }
        }
    }

    // MERGE SORT - SPLIT -----------------------------------------------------------------------

    private static ArrayList<Integer> split(ArrayList<Integer> alist, int startPos, int endPos) {
        ArrayList<Integer> array2=new ArrayList<>();

        if (startPos < endPos) {

//        displayArray(alist);
//        System.out.println();
            int middlePos = ((startPos + endPos) / 2);

            ArrayList<Integer> leftarray = split(alist, startPos, middlePos);
            ArrayList<Integer> rightarray = split(alist, middlePos + 1, endPos);

           displayArray(leftarray);
            displayArray(rightarray);
            System.out.println();

        // addiere die 2 Arrays

        int i = 0, j = 0, k = 0;
        int max_i = leftarray.size() - 1;
        int max_j = rightarray.size() - 1;
        while ((i <= max_i - 1) && (j <= max_j - 1)) {
            if (leftarray.get(i) < rightarray.get(j)) {
                array2.add(leftarray.get(i));
                i++;
            } else {
                array2.add(rightarray.get(j));
                j++;
            }
            k++;
        }

        for (int x = i; x <= max_i; x++) {
            array2.add(leftarray.get(x));
            k++;
        }

        for (int x = j; x <= max_j; x++) {
            array2.add(rightarray.get(x));
            k++;
        }
    }

    return new ArrayList<Integer>( alist.subList(startPos, endPos));




    }
    // --------------------------------------------------------------------------------------------

    public static void recSortArray(ArrayList<Integer> alist, int iteration){
        if (iteration==1) return;

        int lastIndex;

        if (iteration == -1){
            lastIndex = alist.size()-1;
            iteration = lastIndex;
        } else {
            lastIndex = iteration;
        }


        for (int i=0; i<=lastIndex-1 ; i++){
            if (alist.get(i) > alist.get(i+1) ){
                int temp = alist.get(i) ;
                alist.set(i,alist.get(i+1));
                alist.set(i+1,temp);
            }
        }
        recSortArray(alist,iteration-1);

    }


    public static void displayArray(ArrayList<Integer> alist){

        if( alist == null) return;

        int lastIndex = alist.size()-1;

        for (int i=0; i<= alist.size()-1 ; i++){
           // (i == (alist.size() - 1)) ? System.out.print(alist.get(i) + ",") : System.out.println(alist.get(i));
            if (i != (alist.size() - 1)) {
                System.out.print(alist.get(i) + ",");
            } else {
                System.out.println(alist.get(i));
            }
        }
    }

    public static void main(String[] args) {

        // defining the arraylist and adding values
        ArrayList<Integer> numbersOriginal = new ArrayList<Integer>();

        // Variable needed to estimate time of execution
        long now1, now2;

        // Generating random numbers inn the list
        System.out.println("Generating list of numbers to sort ...");
        Random rand = new Random();
        for (int i=1 ; i<= 50 ; i++){
            numbersOriginal.add(rand.nextInt(100));
        }

        ArrayList<Integer> numbers = new ArrayList<Integer>(numbersOriginal);

        // display the array before sort
        displayArray(numbers);

//        // create an object of Scanner
//        Scanner input = new Scanner(System.in);
//
//        // take input from the user
//        System.out.println("1 - NONrecursive ; 2 - Recursive : ");
//        int sortMethod = input.nextInt();

        now1 = System.currentTimeMillis() ;

        System.out.println("\n\rSorting the array WITHOUT recursion ....");
        BoubleSort.sortArray(numbers);
        now2 = System.currentTimeMillis() ;

        // Display the array after sort
        displayArray(numbers);

        System.out.println("Time needed 2 sort (miliseconds) : "+(now2-now1));

        // reinit the array with the same values

        numbers = new ArrayList<Integer>(numbersOriginal);


        now1 = System.currentTimeMillis() ;
            // Sending the array to sort methode - recursive
            System.out.println("\n\rSorting the array WITH  recursion ....");
            BoubleSort.recSortArray(numbers,-1);now2 = System.currentTimeMillis() ;
        // Display the array after sort
        displayArray(numbers);
        System.out.println("Time needed 2 sort (miliseconds) : "+(now2-now1));




        // reinit the array with the same values
        numbers = new ArrayList<Integer>(numbersOriginal);

        // Merge sort
        numbers= split(numbers,0, numbers.size()-1);
        // Display the array after sort

        System.out.println("Merge Sort:");
        displayArray(numbers);

    }
}
