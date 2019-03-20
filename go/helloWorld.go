package main

import (
	"fmt"
	"go/types"
	"os"
	"runtime"
)
const Pi = 3.141592653589;	//常量定义

const (
	aa = iota
	bb
	cc
)

func main()  {
	var goos string = runtime.GOOS
	fmt.Printf("The operating system is: %s\n", goos)
	path := os.Getenv("PATH")
	fmt.Printf("Path is %s\n", path)

	fmt.Println("Hello World")
	fmt.Println(sum(10,12))
	fmt.Println(operate(9,3))
	fmt.Println(twice(7))
	fmt.Println(aa,bb,cc)
}

func sum(a,b int)(int){
	return a+b;
}

func add(a,b int) types.Func {
	return types.Func{};
}

func operate(a,b int)(sum,sub,multi,divide int){
	ss := a*2;
	fmt.Println(ss)
	return a+b,-b,a*b,a/b;
}

type IZ int;

var a IZ = 5;
func twice(a IZ)(int){
	return int(a*2);
}

func init() {
	fmt.Println("This is init method")
}


