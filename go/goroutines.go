package main

import (
	"fmt"
	"time"
)

func main() {
	fmt.Println("in Main");
	go longWait();
	go shortWait();

	fmt.Println("main ready to sleep")
	time.Sleep(10 * 1e9)
	fmt.Println("end of main")
}

func longWait() {
	fmt.Println("in longWait")
	time.Sleep(5 * 1e9)
	fmt.Println("end of longWait")
}

func shortWait() {
	fmt.Println("in shortWait")
	time.Sleep(2 * 1e9)
	fmt.Println("end of shortWait")
}