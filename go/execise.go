package main

import "fmt"

func main()  {
	ptn(10)
}

func ptn(i int)  {
	if(i == 0){
		return
	}else{
		fmt.Println(i);
		ptn(i-1)
	}
}

