package main

import "fmt"

func main() {
	arr := [...]string{"Beijing","Shanghai","Hangzhou","Wuhan"}	//声明的是数组
	// arr := []string{"Beijing","Shanghai","Hangzhou","Wuhan"}	//声明的是切片
	fmt.Printf("type of arr:%T\n",arr)
	// slicePrint(arr) //如果声明的是数组则不能使用该方法，函数的参数是切片类型，[4]string和[3]string是不同的类型，与切片确实不同类型
	slicePrint(arr[:])	//声明的是数组或者切片都可以使用该方法
	fmt.Println(arr)

	slice := []string{"Beijing","Shanghai","Hangzhou","Wuhan","Guangzhou"}
	fmt.Printf("lenth of slice :%v,cap of slice:%v\n",len(slice),cap(slice))
	slice = append(slice,"Nanjing")
	fmt.Printf("lenth of slice :%v,cap of slice:%v\n",len(slice),cap(slice))
}

func slicePrint(slice []string) {
	fmt.Printf("type of slice:%T \n",slice)
	fmt.Println(slice)
	slice = append(slice,"Chengdu")
}