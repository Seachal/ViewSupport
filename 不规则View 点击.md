## 实现过程的核心

## 总结
* Region 的区域检测。
* Matrix 的坐标映射。


1 .通过 Path.setPatch(), 生成 Region，
2. 事件分发时， 判断手指按下的坐标点是否在 通过 Region范围内；
   确定点击区域，然后调用区域的监听。
3. 注意正常情况坐标原点在左上角， 现在 Canvas发生了平移，用Matrix做 Region平移吗？


