package main

import "fmt"

func main() {
	f1()
	deferSeg()
	b()
}

func f1()  {
	fmt.Println("before f2 exec")
	defer f2()
	fmt.Println("after f2 exec")
}
func deferSeg()  {
	i := 0;
	defer fmt.Println(i);
	i++
	return
}

func f2()  {
	fmt.Println("f2 exec")
}


func trace(s string) string {
	fmt.Println("entering:", s)
	return s
}

func un(s string) {
	fmt.Println("leaving:", s)
}

func a() {
	defer un(trace("a"))
	fmt.Println("in a")
}

func b() {
	defer un(trace("b"))
	fmt.Println("in b")
	a()
}