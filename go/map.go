package main

import "fmt"

var count int;

func main() {
	hashmap := make(map[string]int);
	hashmap["apple"] = 2
	hashmap["orange"] = 3

	fmt.Println(hashmap)

	hashmap1 := map[string]func() int{
		"add":add,
		"sub":sub,
	}

	fmt.Println(hashmap1["add"])
	fmt.Println(hashmap1["add"]())
	fmt.Println(hashmap1["sub"])
	fmt.Println(hashmap1["sub"]())
}

func add() int {
	count++
	count++
	return count
}

func sub() int {
	count--
	return count
}