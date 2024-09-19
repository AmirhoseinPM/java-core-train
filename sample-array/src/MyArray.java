public class MyArray {
    private int count;
    private int[] items;

    public MyArray(int length) {
        items = new int[length];
    }

    public void insert(int item) {
        if (count == items.length) {
            int[] newArray = new int[count * 2];
            for (int i=0; i < items.length; i++)
                newArray[i] = items[i];
            items = newArray;
        }
        items[count++] = item;
    }

    public void delete(int item){
        for (int i=0; i < items.length; i++) {
            if (items[i] == item) {
                for(int j= i; j < count - 1; j++)
                    items[j] = items[j + 1];
                count -= 1;
            }
        }
    }

    public void removeAt( int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException();

        for (int i= index; i < count - 1; i++)
            items[i] = items[i+1];
        count -= 1;
    }

    public int indexOf(int item) {
        for(int i=0; i < count; i++){
            if (items[i] == item)
                return i;
        }
        return -1;
    }

    public void print(){
        for (int i = 0; i < count; i++)
            System.out.println(items[i]);
    }
}
