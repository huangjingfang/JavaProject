package main

import (
	"fmt"
	"time"
)

func main() {
	ch := make(chan string)
	go Sender(ch)
	//任意一个函数不使用协程来进行处理都会被认为是发生了死锁
	go Read(ch)
	time.Sleep(1e9)
}

func Sender(out chan string) {
	out <- "Beijing"
	out <- "Hangzhou"
	out <- "Wuhan"
	out <- "Guangzhou"
}

func Read(in chan string) {
	var input string
	for {
		input = <-in
		fmt.Println(input)
	}
}
