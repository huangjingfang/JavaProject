package main

import (
	"fmt"
)

type inter interface{}

func main(){
	str := "assert"
	bo := false
	var any inter;
	any = str
	assertString(&any)
	assertBool(&any)

	any = bo;
	assertString(&any)
	assertBool(&any)
}

func assertString(any *inter){
	v,ok:= (*any).(string)
	fmt.Println(v,ok)
}
func assertBool(any *inter){
	v,ok:= (*any).(bool)
	fmt.Println(v,ok)
}