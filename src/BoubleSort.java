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
// COMBINE 2 ARRAYS ----------------------------------------------------------------
    private static ArrayList<Integer> combineArrays(ArrayList<Integer>leftarray, ArrayList<Integer>rightarray){
        int i=0,j=0,k=0;
        ArrayList<Integer> array2=new ArrayList<>();

        int max_i = leftarray.size() - 1; // last index on "i"
        int max_j = rightarray.size() - 1; //last index on "j"

        while ((i <= max_i ) && (j <= max_j )) {
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
        return array2;
    }
    // MERGE SORT - SPLIT -----------------------------------------------------------------------

    private static ArrayList<Integer> split(ArrayList<Integer> alist, int startPos_inc, int endPos_excl) {
        ArrayList<Integer> array2=new ArrayList<>();
//        System.out.println("#"); // signals and enter in the split procedure
        int endPos= endPos_excl-1;

        if (startPos_inc < endPos) {

            int middlePos = (startPos_inc + endPos) / 2 ;

            System.out.print("Splitting the array:");displayArray(new ArrayList<Integer>(alist.subList(startPos_inc,endPos_excl)));System.out.print(" with :");
            System.out.println("start:("+startPos_inc+")->stop:("+endPos+") Mitte:"+middlePos);
            System.out.print(" => Left list :");displayArray(new ArrayList<Integer>(alist.subList(startPos_inc, middlePos+1)));System.out.println();
            System.out.print(" => Right list:"); displayArray(new ArrayList<Integer>(alist.subList(middlePos+1,endPos+1)));System.out.println("\n\r");



            ArrayList<Integer> leftArray = split(alist, startPos_inc, middlePos+1);
            ArrayList<Integer> rightArray = split(alist, middlePos + 1, endPos_excl);

            System.out.print("Arrays to combine:");
            System.out.print("left :(");displayArray(leftArray);
            System.out.print(") --  ");
            System.out.print("right:(");displayArray(rightArray);
            System.out.print(")  => comb. array: (");

            array2 = combineArrays(leftArray,rightArray);

            displayArray(array2);System.out.println(")");
        }
        else {
            array2=new ArrayList<Integer>(alist.subList(startPos_inc,endPos_excl));
        }

    return array2;




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
                System.out.print(alist.get(i));
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
        for (int i=1 ; i<= 6000 ; i++){
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

        System.out.println("Merge sort --------------");

        // uncomment to define the numbers manually
//        numbers.clear();
//        numbers.add(211);numbers.add(27);numbers.add(19);numbers.add(39);numbers.add(25);numbers.add(65);numbers.add(44);numbers.add(34);numbers.add(314);

        System.out.println("Array to sort, starting values :");
        displayArray(numbers);
        ArrayList<Integer> temp_numbers = new ArrayList<Integer>(numbers);
        System.out.println();
        //---------------------------------


        now1 = System.currentTimeMillis() ;
        numbers= split(numbers,0, numbers.size()-1+1);
        now2 = System.currentTimeMillis() ;

        // Display again array before sort .....
        System.out.println("Test array to sort, starting values :");
        displayArray(temp_numbers);


        //---------------------------------
        // Display the array after sort
        System.out.println("Merge Sort output:");
        displayArray(numbers);
        System.out.println();
        System.out.println("Time needed 2 sort (miliseconds) : "+(now2-now1));

    }
}
