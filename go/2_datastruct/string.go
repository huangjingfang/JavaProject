package main

import "fmt"

func main() {
	a := "Hangzhou"
	b := "Hangzhou"
	fmt.Println(a==b,&a==&b)	// true,false
}