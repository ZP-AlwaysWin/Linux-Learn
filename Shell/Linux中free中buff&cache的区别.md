# `Linux`中`free`中`buff&cache`的区别

## Linux上的指令

```
[root@moons profile.d]# free -h
              total        used        free      shared  buff/cache   available
Mem:            62G        2.2G         58G        167M        1.6G         59G
Swap:            0B          0B          0B

```


## `free`中的`buffer`和`cache`

`redhat`对`free`输出的解读

两者都是`RAM`中的数据。简单来说，**`buffer`是即将要被写入磁盘的，而`cache`是被从磁盘中读出来的。** (`free`中的`buffer`和`cache`它们都是占用内存的)

- `A buffer is something that has yet to be "written" to disk.`

- `A cache is something that has been "read" from the disk and stored for later use.`

### `buffer`

`buffer` : 作为`buffer cache`的内存，是块设备的写缓冲区。`buffer`是根据磁盘的读写设计的，把分散的写操作集中进行，减少磁盘碎片和硬盘的反复寻道，从而提高系统性能。`linux`有一个守护进程定期清空缓冲内容（即写如磁盘），也可以通过`sync`命令手动清空缓冲。`buffer`是由各种进程分配的，被用在如输入队列等方面，一个简单的例子如某个进程要求有多个字段读入，在所有字段被读入完整之前，进程把先前读入的字段放在`buffer`中保存。

### `cache`

`cache`: 作为`page cache`的内存, 文件系统的`cache`。`cache`经常被用在磁盘的`I/O`请求上，如果有多个进程都要访问某个文件，于是该文件便被做成`cache`以方便下次被访问，这样可提供系统性能。`cache`是把读取过的数据保存起来，重新读取时若命中（找到需要的数据）就不要去读硬盘了，若没有命中就读硬盘。其中的数据会根据读取频率进行组织，把最频繁读取的内容放在最容易找到的位置，把不再读的内容不断往后排，直至从中删除。　　如果 `cache` 的值很大，说明`cache`住的文件数很多。如果频繁访问到的文件都能被`cache`住，那么磁盘的读`IO bi`会非常小。
