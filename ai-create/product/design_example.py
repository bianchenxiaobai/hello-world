#!/usr/bin/python3
# 一个例子
import tkinter
import pyautogui as pag
from PIL import Image, ImageTk
"""
窗口绑定事件例子
"""


def callback(event):
    print('('+str(event.x)+","+str(event.y)+')')


top = tkinter.Tk()
# 窗口删除标题
# top.overrideredirect(True)
# 设置窗口透明度
# top.attributes('-alpha', 0.6)
frame = tkinter.Frame(top, width=600, height=400)
# 绑定鼠标左键处理函数
frame.bind("<Button-1>", callback)
frame.pack()
# init
"""
mouseLocX = 0
mouseLocY = 0
sub_top = None
draw_img = None
canvas = None
"""
# button
"""
example_btn = tkinter.Button(top, text="按钮", command=function_name)
example_btn.pack()
"""
# 进入消息循环
top.mainloop()
