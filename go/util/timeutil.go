package util

import (
	"time"
	"fmt"
)
//实验结果证明stop并没有被争取赋值
func Timecompare(function func()) (time.Time,time.Time) {
	var start,stop time.Time
	trace(&start)
	defer trace(&stop)
	function()
	return start,stop;
}

func Log(function func()) {
	start := time.Now()
	defer end(&start)
	function();
}


func trace(result *time.Time) {
	*result = time.Now();
}

func end(start *time.Time) {
	stop := time.Now();
	duration := stop.Sub(*start);
	fmt.Println("time consumption:",duration.Seconds(),"s")
}