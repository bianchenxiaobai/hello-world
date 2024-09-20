import tkinter
"""
创建了一个透明画布的窗口
透明窗口与非透明窗口鼠标事件分发问题
正常完全透明后鼠标事件不会被接收
"""
# 创建窗口
window = tkinter.Tk()
# 窗口标题
window.title("分钱问题")

# 创建画布
canvas = tkinter.Canvas(window, width=800, height=500, bg="white")
window.attributes('-transparentcolor', "white")
# 绘制矩形(x1,y1,x2,y2),填充颜色：blue，边框颜色：blue
canvas.create_rectangle(100, 100, 120, 150, fill='blue', outline='blue')
# 使用tag标识矩形
canvas.create_rectangle(200, 200, 220, 250, fill='red', outline='red', tag="red")
# 包装画布
canvas.pack()
# 删除指定图形
canvas.delete("red")

# 运行并显示窗口
window.mainloop()