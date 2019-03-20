package main

import "fmt"

func main(){
	fmt.Println(params(4,5,6,7))
	arr := []int{4,5,6,8};
	fmt.Println(params(arr...))
	fmt.Println(arrayAdd(arr))
	fmt.Println()

	a := 0;
	value := &a
	modifyOutside(5,6,value)
	fmt.Println(*value)

	for i:=0;i<10;i++{
		fmt.Printf("fibonacci( %d ) is :%d \n ",i,fibonacci(i))
	}
}

func params(args ... int) int {
	var sum int
	for _,val := range args {
		sum += val;
	}
	return sum
}

func arrayAdd(arr []int) int{
	//var result int;
	//for _,value := range arr{
	//	result += value;
	//}
	//return result;
	return params(arr...)
}

func modifyOutside(a,b int,value *int){
	*value = a*b;
}

func fibonacci(i int) int {
	if i== 0 || i == 1{
		return 1;
	}else{
		return fibonacci(i-1)+ fibonacci(i-2);
	}
}