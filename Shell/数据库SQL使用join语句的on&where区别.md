# 数据库`SQL`使用`join`语句的`on&where`区别

在使用 `join` 时，`on` 和 `where` 条件的区别如下：

 1、`on` 条件是在生成临时表时使用的条件，它不管 `on` 中的条件是否为真，都会返回左边表中的记录。
 
 2、`where` 条件是在临时表生成好后，再对临时表进行过滤的条件。这时已经没有 `left join` 的含义（必须返回左边表的记录）了，条件不为真的就全部过滤掉。
 
 
 ## 参考资料
 
 http://www.runoob.com/w3cnote/sql-join-the-different-of-on-and-where.html
