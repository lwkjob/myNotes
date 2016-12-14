package com.lwk.algorithm.sort;

public class rot{
	public static void main(String args[]){
		int y=0;
		int a[]={1,2,3,4,5,6,7};
		y=px(a,7);
		if(y!=-1){
			System.out.print("你要的数在第"+(y+1)+"位");
		}else{
			System.out.print("没有你要找的数");
		}
	}
	public static int px(int[] a,int x){		
		int left=0;
		int rigth=a.length-1;		
		int m=0;
		while(left<=rigth){
			m=(rigth+left)/2;
			if(x==a[m]){
				return m;
			}else if(a[m]>x){				
				rigth=m-1;
				m=-1;	
			}else{				
				left=m+1;
				m=-1;
			}			
		}		
		return m;
	}
}
