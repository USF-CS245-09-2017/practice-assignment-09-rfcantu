public class BinaryHeap {

    float[] data;
    int size;
    int FRONT = 1;

    BinaryHeap()
    {
        data = new float[10];
        size = 0;
    }

    public void add(float item) // add an int instance to the priority queue
    {
        if (isFull()) // if we need more space
        {
            doubleSize();
        }

        data[++size] = item;
        int current = size;

        while (data[current] < data[parent(current)]) //this is heapifyUp (note: could have done this better)
        {
            swap(data, current, parent(current));
            current = parent(current);
        }

        if (size > 1)
            minHeap();

    }

    int parent(int pos) // get the parent of the current node
    {
        return pos / 2;
    }

    int leftChild(int pos) // get left child of the current node
    {
        return 2 * pos;
    }

    private int rightChild(int pos) // return right child of the current node
    {
        return (2 * pos) + 1;
    }

    boolean isLeaf(int pos) // find out if the node has no children
    {
        if (pos >= (size / 2) && pos <= size) { // first half checks if the position is low enough in the heap, second half makes sure its not too big
            return true;
        }
        return false;
    }


    public int remove() throws ArrayIndexOutOfBoundsException  // remove highest priority item, the lowest number, from the queue, return exception if queue is empty
    {
        if (isEmpty())
            throw new ArrayIndexOutOfBoundsException(); // throw exception here

        int returner = (int)data[FRONT]; // store the data
        data[FRONT] = data[size--];
        minHeapify(FRONT); // remove the data from the heap
        return returner;
    }

    void minHeapify(int pos)
    {
        if (!isLeaf(pos)) { // is the node non leaf?
            if (data[pos] > data[leftChild(pos)] || data[pos] > data[rightChild(pos)]) { // and is it greater than its children
                if (data[leftChild(pos)] < data[rightChild(pos)]) { // swap with the left child
                    swap(data, pos, leftChild(pos));
                    minHeapify(leftChild(pos)); // do it again
                }
                else { // swap with the right child
                    swap(data, pos, rightChild(pos));
                    minHeapify(rightChild(pos)); // do it again
                }
            }
        }
    }

    void minHeap() // sort all of the nodes
    {
        for (int pos = (size / 2); pos >= 1; pos--)
        {
            minHeapify(pos);
        }
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    void swap(float[] arr, int x, int y)
    {
        float temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    boolean isFull()
    {
        return size == data.length - 1;
    }

    void doubleSize()
    {
        float[] temp = new float[data.length * 2];
        for (int i = 0; i < data.length; i++)
        {
            temp[i] = data[i];
        }

        data = temp;
    }

}