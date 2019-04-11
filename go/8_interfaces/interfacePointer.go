package main

import "fmt"

type List []int

func (l List) Len() int {
	return len(l)
}
func (l *List) Append(val int) {
	*l = append(*l, val)
}

type Appender interface {
	Append(int)
}

func countInto(a Appender, start, end int) {
	for i := start; i < end; i++ {
		a.Append(i)
	}
}

type Lener interface {
	Len() int
}

func longEnough(l Lener) bool {
	return l.Len() > 5
}

func main() {
	//var lst List;
	//countInto(lst,0,5);
	plst := new(List)
	countInto(plst, 0, 5)
	if longEnough(plst) {
		fmt.Println("plst is long enough")
	}
}
