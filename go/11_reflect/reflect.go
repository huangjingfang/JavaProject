package main

import (
	"fmt"
	"reflect"
)

type myInt int

func main() {
	reflectType()
	divider("reflectBasic")
	reflectBasic()
	divider("reset")
	reset()
}
func divider(str string) {
	fmt.Printf("\n*****************%s*****************\n", str)
}

func reflectType() {
	//TypeOf返回的是变量的类型 t，t的Kind方法返回的则是变量的底层类型，可参看myint的类型，如果t是指针的话将不会包含指针的类型。
	//ValueOf放回的是变量的值 v,如果是变量则是变量的值，如果是指针则是指针的地址，如果是reflect.rtype类型的指针，则是string类型的指针（暂时也不清楚原理）
	// v的Kind方法返回的是值的类型（底层类型），相当于TypeOf的Kind的方法；v.Type返回的是值的类型，相当于TypeOf的值
	str := "reflect"
	var t, v = reflect.TypeOf(str), reflect.ValueOf(str)
	fmt.Println(t, t.Kind(), v, v.Kind(), v.Type())

	t, v = reflect.TypeOf(&str), reflect.ValueOf(&str)
	fmt.Println(t, t.Kind(), v, v.Kind(), v.Type())

	var tt, vv = reflect.TypeOf(t), reflect.ValueOf(t)
	fmt.Println(tt, tt.Kind(), vv, vv.Kind(), vv.Type())

	var myint = myInt(12)
	var mt, mv = reflect.TypeOf(myint), reflect.ValueOf(myint)
	fmt.Println(mt, mt.Kind(), mv, mv.Kind(), mv.Type())
}
func reflectBasic() {
	str := "reflect"
	t, v := reflect.TypeOf(str), reflect.ValueOf(str)
	fmt.Println("type:", t)
	fmt.Println("value:", v)
	fmt.Println("type:", v.Type())
	fmt.Println("kind", v.Kind())
	fmt.Println("value:", v.String())
	fmt.Println(v.Interface())
}

func reset() {
	str := "reflect"
	v := reflect.ValueOf(str)
	//v.SetString("go reflect")
	//fmt.Println(str)
	fmt.Println("set by Value", v.CanSet(), "\tstr's value:", str)

	/***
	*不能通过上述方法进行设值，因为reflect.ValueOf()传入的参数是str的一个拷贝，所以无法做到修改str的值，如果想要实现通过反射修改str的值，则需要传入指针类型。
	 */

	v = reflect.ValueOf(&str)
	v = v.Elem()

	v.SetString("go reflect")
	fmt.Println("set by Pointer", v.CanSet(), "\tstr's value:", str)
}
