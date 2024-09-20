#!/usr/bin/python3
# 一个截图工具的例子
import tkinter
import pyautogui as pag
from PIL import Image, ImageTk


def callback(event):
    print('('+str(event.x)+","+str(event.y)+')')


def get_capture(a1, b1, a2, b2):
    if a2 >= a1:
        x2 = a2
        x1 = a1
    else:
        x2 = a1
        x1 = a2
    if b2 >= b1:
        y2 = b2
        y1 = b1
    else:
        y2 = b1
        y1 = b2
    print(x1, y1, x2, y2)
    img = pag.screenshot(region=(x1, y1, x2-x1, y2-y1))
    return img


def save_capture(img, file):
    img.save(file)


def capture_click(event):
    global mouseLocX, mouseLocY
    print('点击了('+str(event.x)+","+str(event.y)+')')
    print('当前记录值为(' + str(mouseLocX) + "," + str(mouseLocY) + ')')
    if mouseLocX == 0 and mouseLocY == 0:
        mouseLocX = event.x
        mouseLocY = event.y
        print('记录点')


log_img = None
show_img = None


def capture_release(event):
    global mouseLocX, mouseLocY
    global log_img, show_img  # 不是全局变量会被回收
    if mouseLocX != event.x and mouseLocY != event.y:
        # save_capture(get_capture(mouseLocX, mouseLocY, event.x, event.y), "capture.png")
        # log_img = Image.open("capture.png")
        log_img = get_capture(mouseLocX, mouseLocY, event.x, event.y)
        show_img = ImageTk.PhotoImage(log_img)
        label = tkinter.Label(frame, image=show_img)
        label.pack()
        mouseLocX = mouseLocY = 0
        sub_top.destroy()
        top.focus_force()
        print('截图(退出)')


def motion_rect(event):
    print('motion('+str(event.x)+","+str(event.y)+')')
    # 应该产生透明的画布，这样截图效果更佳（自带截图工具的实现方式）
    # canvas.create_rectangle(mouseLocX, mouseLocY, event.x, event.y, fill='blue', outline='blue')
    # canvas.create_rectangle(mouseLocX, mouseLocY, event.x, event.y, outline='blue')


top = tkinter.Tk()
# 窗口删除标题
# top.overrideredirect(True)
# 设置窗口透明度
# top.attributes('-alpha', 0.6)
frame = tkinter.Frame(top, width=300, height=100)
# 绑定鼠标左键处理函数
frame.bind("<Button-1>", callback)
frame.pack()
# init
mouseLocX = 0
mouseLocY = 0
sub_top = None
draw_img = None
canvas = None


def create_sub_top():
    # 产生子窗口
    global sub_top, canvas
    sub_top = tkinter.Toplevel()
    sub_top.title("子窗口")
    w = top.winfo_screenwidth()
    h = top.winfo_screenheight()
    # 子窗口无标题化，接近透明化，全屏化
    sub_top.overrideredirect(True)
    # 完全透明同时也没有了焦点
    # sub_top.attributes('-alpha', 0.01)
    sub_top.geometry("%dx%d" % (w, h))
    # 产生子窗口画布
    cat_img = pag.screenshot()
    # dog_img = Image.open('resource/back.jpg')
    # sub_top.attributes('-transparentcolor', "white")
    canvas = tkinter.Canvas(sub_top, width=w, height=h, bg="white")
    global draw_img
    draw_img = ImageTk.PhotoImage(cat_img)
    canvas.create_image(w/2, h/2, image=draw_img)
    canvas.pack()
    # 子窗口点击处理
    global mouseLocX, mouseLocY
    mouseLocX = mouseLocY = 0
    sub_top.bind("<Button-1>", capture_click)
    sub_top.bind("<ButtonRelease-1>", capture_release)
    sub_top.bind("<B1-Motion>", motion_rect)
    # <ButtonRelease-1>


def save_current_capture():
    save_capture(log_img, "../pagtest/captureResource/save.png")


# button
cut_btn = tkinter.Button(top, text="截图", command=create_sub_top)
cut_btn.pack()
save_btn = tkinter.Button(top, text="保存", command=save_current_capture)
save_btn.pack()
# 进入消息循环
top.mainloop()
