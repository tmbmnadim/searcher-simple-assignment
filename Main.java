import algs.BinarySearch;

class Main {
    public static void main(String[] args) {
        int key = 14;
        int[] items =  {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        
        BinarySearch bs = new BinarySearch(items);
        bs.search(14);
    }
}