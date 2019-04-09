package main

import "fmt"

func main() {
	defer fmt.Println("defer 0") //是否会打印defer 0？
	defer panic("panic in defer")
	defer fmt.Println("defer 1")

	fmt.Println("Brfore panic")
	panic("Panic test")
	fmt.Println("after panic") //理论上这行代码应该不会执行，可以在panic前加defer使这行代码执行
}
