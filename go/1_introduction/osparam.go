package main

import (
	"fmt"
	"os"
	"strings"
)

//os.Args的第一个参数是程序的名称，直接使用go run xxx.go ...param 和go build xxx.go;xxx.exe ...param的第一个参数结果不一样，
//因为可执行程序的名称和位置不一样
func main() {
	for i:=0;i<len(os.Args);i++{
		fmt.Printf("The %d param is: %s \n",i,os.Args[i])
	}
	fmt.Println(strings.Join(os.Args,"\t"))
}