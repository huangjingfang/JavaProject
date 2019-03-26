package main

import "fmt"

func main() {
	var p *int
	// p = new(int) //如果不给指针赋初值或者开辟内存空间就直接赋值，将会引发panic
	*p = 2

	fmt.Println(p,*p)
}