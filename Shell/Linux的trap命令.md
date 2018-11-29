# Linux的trap命令

我们在写`Shell`脚本的时候，执行某些自动化操作的时候，其实是很担心有人认为的通过键盘输入一些中断进程的指令的，我之前一直不知道该怎么处理这种情况，但是今天通过看别人脚本，偶然的机会看见了`trap`命令。

真是久旱逢甘霖~~哈，其实也没啥用，还是要在操作手册上大写几个字，执行脚本时，请不要执行自杀性操作。

## 1. Linux信号

Linux系统利用信号与系统中的进程进行通信。Linux的常见信号有：

| 信号 | 值      | 描述                           |
| ---- | ------- | ------------------------------ |
| 1    | SIGHP   | 挂起进程                       |
| 2    | SIGINT  | 终止进程                       |
| 3    | SIGQUIT | 停止进程                       |
| 9    | SIGKILL | 无条件终止进程                 |
| 15   | SIGTERM | 尽可能终止进程                 |
| 17   | SIGSTOP | 无条件停止进程，但不是终止进程 |
| 18   | SIGTSTP | 停止或暂停进程，但不终止进程   |
| 19   | SIGCONT | 继续运行停止的进程             |

## 2. 信号组合键

Ctrl+C组合键会产生SIGINT信号，Ctrl+Z会产生SIGTSTP信号。

## 3. trap命令

trap命令允许你来指定shell脚本要监视并拦截的Linux信号。trap命令的格式为：`trap commands signals`。

### demo1

shell脚本：

```
#!/bin/bash
# test trap command
trap "echo 'Sorry! I have trapped Ctrl-C'" SIGINT

echo This is a test script

count=1
while [ $count -le 10 ]
do
  echo "Loop $count"
  sleep 1
  count=$[ $count + 1 ]
done

echo The end.
```

运行结果：

```
This is a test script
Loop 1
Loop 2
^CSorry! I have trapped Ctrl-C
Loop 3
Loop 4
^CSorry! I have trapped Ctrl-C
Loop 5
Loop 6
Loop 7
Loop 8
^CSorry! I have trapped Ctrl-C
Loop 9
Loop 10
The end.
```

### demo2

除了在shell脚本中捕获信号外，也可以在shell退出时捕获，在trap命令后加上EXIT信号就行。

shell脚本：

```
#!/bin/bash
# test trap command
trap "echo Goodbye." EXIT

echo This is a test script

count=1
while [ $count -le 10 ]
do
  echo "Loop $count"
  sleep 1
  count=$[ $count + 1 ]
done

echo The end.
```

运行结果：

```
This is a test script
Loop 1
Loop 2
Loop 3
Loop 4
Loop 5
Loop 6
Loop 7
Loop 8
Loop 9
Loop 10
The end.
Goodbye.
```

### demo3——修改trap操作

```
#!/bin/bash
# test trap command

trap "echo 'Sorry! I have trapped Ctrl-C'" SIGINT

count=1
while [ $count -le 5 ]
do
  echo "Loop $count"
  sleep 1
  count=$[ $count + 1 ]
done


trap "echo 'Sorry! The trap has been modified.'" SIGINT

count=1
while [ $count -le 5 ]
do
  echo "Loop $count"
  sleep 1
  count=$[ $count + 1 ]
done

echo The end.
```

运行结果：

```
Loop 1
Loop 2
Loop 3
^CSorry! I have trapped Ctrl-C
Loop 4
Loop 5
Loop 1
Loop 2
Loop 3
^CSorry! The trap has been modified.
Loop 4
Loop 5
The end.
```

### demo4

删除捕获，命令形式为：`trap -- ***`，例如`trap -- SIGINT`

## 参考资料

1. Linux命令行与shell脚本大全

