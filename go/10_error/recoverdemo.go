package main

import "fmt"

func main() {
	defer func() {
		if err := recover(); err != nil {
			fmt.Println("Catch from panic：", err)
		}
	}()
	badCall()
	fmt.Println("After bad Call") //是否会执行？
}

func badCall() {
	panic("Bad call")
}
