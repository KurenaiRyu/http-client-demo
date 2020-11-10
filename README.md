# Http Client Demo
记录一下用使用HttpOk进行http请求，方便日后直接套用。  
主要是Jackson转换时遇到泛型擦写问题卡了挺久。想到的方法是由请求对象写一个getter，new一个TypeReference给调用代码。  
结构有参考京东sdk。