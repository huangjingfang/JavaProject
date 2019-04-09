package main

import "fmt"

func main() {
	//outOfIndex()
	appendDemo()
}

func outOfIndex() {
	slice := []string{"Wuhan", "Beijing", "Shanghai", "Hangzhou"}
	slice[5] = "Guangzhou"
	fmt.Println(slice, len(slice), cap(slice))
}

func appendDemo() {
	slice := []string{"Wuhan", "Beijing", "Shanghai", "Hangzhou"}
	slice = append(slice, "Guangzhou")
	fmt.Println(slice, len(slice), cap(slice))
}
