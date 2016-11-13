package com.lwk.algorithm;

public class sort {
	public static void main(String args[]) {
		int arry[] = { 2, 4, 1, 8, 5, 9 };
		mppx(arry);
		for (int x = 0; x < arry.length; x++) {
			System.out.print(arry[x] + " ");
		}
	}
	public static void mppx(int[] a) {// 冒泡法排序
		int temp = 0;
		for (int x = 0; x < a.length; x++) {
			for (int y = 1; y < a.length - x; y++) {
				if (a[y] < a[y - 1]) {
					temp = a[y];
					a[y] = a[y - 1];
					a[y - 1] = temp;
				}
			}
		}
	}
	public static void crpx(int[] arr){// 插入法排序
		int temp=0;
		for(int i=0;i<arr.length-1;i++){
			for(int j=i+1;j<arr.length-1;j++){
				if(arr[i]>arr[j]){
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
				}
			}
		}
		
	}
}
