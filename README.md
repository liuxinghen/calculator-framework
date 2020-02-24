# 一个逆波兰式计算框架

初始需求来源于一家IT公司的招聘题目, 要求实现一个接受逆波兰表达式的数学计算器, 支持+,-,*,/,sqrt,redo,clear操作, 在实现过程中体现OO设计, 使用恰当的软件设计模式, 体现个人对于面向对象SOLID思想的理解.

在实现的过程中, 通过对以下四个概念的抽象

1. 计算器 Calculator
2. 操作 Operator
3. 结果 Result
4. 参数提供者 ArgsSupplier

实现了一个逆波兰式计算框架SingleCommandCalculator, 并实现了抽象的AbstractNToOneOperation操作, 和undo/redo/clear操作.

然后, 通过实现BigDecimal的+,-,*,/,sqrt,avgN,input操作, 实现了最初的逆波兰式数学计算器需求.

进一步的, 通过实现了一组String的操作符

1. '+' 操作, 连接两个字符串
2. '-' 操作, 去掉一个字符串中的另一个字符串子串
3. '>' 操作, 升序排列一个字符串中的字母

实现了一个string的逆波兰式计算器, 验证了该逆波兰式计算框架的通用性.

理论上, 该计算框架可以通过实现自定义的操作符操作和Input操作, 实现对任意Java类型的逆波兰表达式动态计算.


