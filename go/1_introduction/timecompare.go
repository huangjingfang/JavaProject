package main

import (
	"../util"
	"fmt"
)
func main() {
	start,stop := util.Timecompare(iterator);
	fmt.Println(start)
	fmt.Println(stop)
}

func iterator(){
	var count int
	for i:=0;i<100000000;i++{
		count += i
	}
}
