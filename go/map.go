package main

import "fmt"

func main() {
	hashmap := make(map[string]int);
	hashmap["apple"] = 2
	hashmap["orange"] = 3

	fmt.Println(hashmap)
}
