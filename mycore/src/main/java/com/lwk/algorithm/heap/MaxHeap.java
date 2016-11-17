package com.lwk.algorithm.heap;

/**
 * Created by liwenke on 2016/11/14.
 */
public class MaxHeap {
    int[ ] heap;
    int heapSize;

    public MaxHeap(int[ ] array) {
        this.heap = array;
        this.heapSize=array.length;
    }

    private int parent(int i){
        return (i-1)/2;
    }

    private int left(int i){
        return (i+1)*2-1;
    }

    private int right(int i){
        return (i+1)*2;
    }

    public void buidMaxHeap(){
        for(int i=heapSize/2-1;i>=0;i--){
            maxify(i);//依次向上将当前字树构建成最大堆
        }
    }

    //递归从建大顶堆
    public void maxify(int i){
        int l=left(i);
        int r=right(i);
        int largest;

        if(l<heapSize&&heap[l]>heap[i])
            largest=l;
        else
            largest=i;
        if(r<heapSize&&heap[r]>heap[largest])
            largest=r;
        if(largest==i||largest>=heapSize)//如果largest等于i说明i是最大元素 largest超出heap范围说明不存在比i节点大的子女
            return ;
        int tmp=heap[i];  //交换i与largest对应的元素位置，在largest位置递归调用maxify
        heap[i]=heap[largest];
        heap[largest]=tmp;
        maxify(largest);


    }

    public static void main(String[] args) {
        int[] array=new int[]{16,14,9,7,8,10,3,2,4,1};
        MaxHeap heap=new MaxHeap(array);
        System.out.println("执行最大堆化前堆的结构：");
        printHeapTree(heap.heap);
        heap.buidMaxHeap();
        System.out.println("执行最大堆化后堆的结构：");
        printHeapTree(heap.heap);
        heap.heapSort();
        System.out.println("执行堆排序后数组的内容");
        printHeap(heap.heap);
    }

    private static void printHeapTree(int[] array)
    {
        for(int i=1;i<array.length;i=i*2)
        {
            for(int k=i-1;k<2*(i)-1&&k<array.length;k++)
            {
                System.out.print(array[k]+" ");
            }
            System.out.println();
        }
    }
    private static void printHeap(int[] array)
    {
        for(int i=0;i<array.length;i++)
        {
            System.out.print(array[i]+" ");
        }
    }

    public void heapSort()
    {
        for(int i=0;i<heap.length;i++)
        {
            //执行n次，将每个当前最大的值放到堆末尾
            int tmp=heap[0];
            heap[0]=heap[heapSize-1];
            heap[heapSize-1]=tmp;
            heapSize--;
            maxify(0);
        }
    }
}
