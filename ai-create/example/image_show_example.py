import tkinter as tk
from PIL import Image, ImageTk
"""
插入图像与文字的窗口
"""
root = tk.Tk()
root.geometry('650x450+150+100')
root.title('Test')
root.resizable(False, False)
global photo
# 设置条形框,插入图片
image = Image.open("../resource/capture.png")
photo = ImageTk.PhotoImage(image)
label = tk.Label(root, text='..............', compound='center', font=('微软雅黑', 30), image=photo)
label.pack()
root.mainloop()