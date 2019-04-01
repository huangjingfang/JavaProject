package main

import (
	"fmt"
	"os"
)

func main() {
	fmt.Println(declare())
	fmt.Println(nodeclare())
	deferExit() //没有输出结果
}

func declare() int {
	var result int
	defer func() {
		result++
		fmt.Println("defer")
	}()
	return result
}

func nodeclare() (result int) {
	defer func() {
		result++
		fmt.Println("defer")
	}()
	return result
}

func deferExit() {
	defer func() {
		fmt.Println("defer Exit")
	}()
	os.Exit(1)
}
