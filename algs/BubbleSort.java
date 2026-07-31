package algs;

public class BubbleSort implements Sorter {
    private int[] items;
    public BubbleSort(int[] items){
        this.items = items;
    }

    @Override
    public void sort() {
        for(int i = 0; i<items.length;i++) {
            for(int j = 0; j < items.length-1;j++) {
                if(items[j] > items[j+1]) {
                    int temp = items[j];
                    items[j] = items[j+1];
                    items[j+1] = temp;
                }
            }
        }
    }
    
}
