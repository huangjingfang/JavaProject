package main

import (
	"fmt"
)

type inter interface{}

func main() {
	str := "assert"
	bo := false
	var any inter
	any = str
	assertString(&any)
	assertBool(&any)

	any = bo
	assertString(&any)
	assertBool(&any)

	fmt.Println("**************************************************************")
	assert(&any)
	any = str
	assert(&any)

}

func assertString(any *inter) {
	v, ok := (*any).(string)
	fmt.Println(v, ok)
}
func assertBool(any *inter) {
	v, ok := (*any).(bool)
	fmt.Println(v, ok)
}
func assert(any *inter) {
	//interface.(type)只能在switch语句中
	//v,ok := (*any).(type)
	//fmt.Println()
	switch (*any).(type) {
	case string:
		fmt.Println((*any).(string))
	case int:
		fmt.Println((*any).(int))
	case bool:
		fmt.Println((*any).(bool))
	default:
		fmt.Println((*any).(string))
	}
}
